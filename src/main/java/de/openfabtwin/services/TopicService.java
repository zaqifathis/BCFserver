package de.openfabtwin.services;

import de.openfabtwin.entities.*;
import de.openfabtwin.exceptions.ConflictException;
import de.openfabtwin.generated.dto.*;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.repositories.TopicEventRepository;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.DateUtils;
import de.openfabtwin.utils.ODataFilterOrderParser;
import de.openfabtwin.utils.OffsetBasedPageRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicEventRepository topicEventRepository;
    private final TopicMapper topicMapper;
    private final EntityManager entityManager;
    private final EntityResolver entityResolver;
    private final SecurityContextService securityContextService;

    @Transactional
    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        if(topicPOST.getTitle() == null || topicPOST.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        var topic = new TopicEntity();
        topic.setProject(entityResolver.resolveProject(projectId));
        topic.setGuid(UUID.randomUUID().toString());
        topic.setTitle(topicPOST.getTitle());
        topic.setReferenceLinks(topicPOST.getReferenceLinks());
        topic.setIndex(topicPOST.getIndex()); //deprecated and will be removed
        topic.setDescription(topicPOST.getDescription());
        topic.setDueDate(DateUtils.toInstant(topicPOST.getDueDate()));
        topic.setCreationAuthor(securityContextService.getCurrentUserEmail());

        Instant createEventTime = Instant.now();
        topic.setCreationDate(createEventTime);

        ExtensionEntity ext = entityResolver.resolveProjectExtension(projectId);
        topic.setTopicType(validateValue(topicPOST.getTopicType(), ext.getTopicTypes()));
        topic.setTopicStatus(validateValue(topicPOST.getTopicStatus(), ext.getTopicStatuses()));
        topic.setPriority(validateValue(topicPOST.getPriority(), ext.getPriorities()));
        topic.setLabels(validateList(topicPOST.getLabels(), ext.getTopicLabels()));
        topic.setAssignedTo(validateValue(topicPOST.getAssignedTo(), ext.getUsers()));
        topic.setStage(validateValue(topicPOST.getStage(), ext.getStages()));

        if(topicPOST.getBimSnippet() != null) {
            BimSnippetEntity snippetEntity = topicMapper.mapBimSnippetEntity(topicPOST.getBimSnippet(), topic);
            topic.setBimSnippet(snippetEntity);
        }
        topicRepository.save(topic);
        entityManager.flush();
        topic.setServerAssignedId("TOPIC_" + topic.getId());

        createTopicEvent(topic, TopicEventType.topic_created, null, createEventTime, topic.getCreationAuthor());
        return topicRepository.save(topic);
    }

    public void delete(String topicId, String projectId) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        List<TopicEntity> referencingTopics = topicRepository.findAllByRelatedTopicsContaining(topic.getGuid());
        for (TopicEntity referencing : referencingTopics) {
            referencing.getRelatedTopics().remove(topic.getGuid());
        }
        topicRepository.saveAll(referencingTopics);
        topicRepository.delete(topic);
    }

    public TopicEntity getById(String topicId, String projectId) {
        return entityResolver.resolveTopic(projectId, topicId);
    }

    public List<TopicEntity> getAll(String projectId, String filter, String orderby, String top, String skip) {
        entityResolver.resolveProject(projectId);
        int limit = (top != null) ? Integer.parseInt(top) : 100;
        int offset = (skip != null) ? Integer.parseInt(skip) : 0;
        Sort sort = ODataFilterOrderParser.parseOrderBy(orderby,"creationDate", TOPIC_ORDER_MAPPING);
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, sort);

        Specification<TopicEntity> spec = (root, query, cb) -> cb.equal(root.get("project").get("guid"), projectId);
        if(filter != null && !filter.isBlank()) {
            spec = spec.and(ODataFilterOrderParser.getFilter(filter, TOPIC_FILTER_MAPPING));
        }
        Page<TopicEntity> topics = topicRepository.findAll(spec, pageable);
        return topics.getContent();
    }

    public TopicEntity update(String topicId, String projectId, TopicPUT topicPUT) {
        TopicEntity existingTopic = entityResolver.resolveTopic(projectId, topicId);
        if (topicPUT.getTitle() == null || topicPUT.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        // Capture before value updated for storing event later
        String beforeTitle = existingTopic.getTitle();
        String beforeDescription = existingTopic.getDescription();
        String beforeStatus = existingTopic.getTopicStatus();
        String beforeType = existingTopic.getTopicType();
        String beforePriority = existingTopic.getPriority();
        Instant beforeDueDate = existingTopic.getDueDate();
        String beforeAssignedTo = existingTopic.getAssignedTo();
        List<String> beforeLabels = existingTopic.getLabels() == null ? List.of() : List.copyOf(existingTopic.getLabels());
        String beforeStage = existingTopic.getStage();

        ExtensionEntity ext = entityResolver.resolveProjectExtension(projectId);

        existingTopic.setTitle(topicPUT.getTitle());
        if(topicPUT.getTopicType() != null) {
            existingTopic.setTopicType(validateValue(topicPUT.getTopicType(), ext.getTopicTypes()));
        }
        if(topicPUT.getTopicStatus() != null) {
            existingTopic.setTopicStatus(validateValue(topicPUT.getTopicStatus(), ext.getTopicStatuses()));
        }
        if(topicPUT.getReferenceLinks() != null) {
            existingTopic.getReferenceLinks().clear();
            existingTopic.getReferenceLinks().addAll(topicPUT.getReferenceLinks());
        }
        if (topicPUT.getPriority() != null) {
            existingTopic.setPriority(validateValue(topicPUT.getPriority(), ext.getPriorities()));
        }
        if(topicPUT.getIndex() != null) {
            existingTopic.setIndex(topicPUT.getIndex());
        }
        if(topicPUT.getLabels() != null) {
            validateList(topicPUT.getLabels(), ext.getTopicLabels());
            existingTopic.getLabels().clear();
            existingTopic.getLabels().addAll(topicPUT.getLabels());
        }
        if(topicPUT.getAssignedTo() != null) {
            existingTopic.setAssignedTo(validateValue(topicPUT.getAssignedTo(), ext.getUsers()));
        }
        if(topicPUT.getStage() != null) {
            existingTopic.setStage(validateValue(topicPUT.getStage(), ext.getStages()));
        }
        if(topicPUT.getDescription() != null) {
            existingTopic.setDescription(topicPUT.getDescription());
        }
        if(topicPUT.getBimSnippet() != null) {
            BimSnippetEntity snippet = existingTopic.getBimSnippet();

            if (snippet == null) {
                snippet = new BimSnippetEntity();
                snippet.getTopics().add(existingTopic);
                snippet.setSnippetType(topicPUT.getBimSnippet().getSnippetType());
                snippet.setIsExternal(Boolean.parseBoolean(topicPUT.getBimSnippet().getIsExternal()));
                snippet.setReference(topicPUT.getBimSnippet().getReference());
                snippet.setReferenceSchema(topicPUT.getBimSnippet().getReferenceSchema());
                existingTopic.setBimSnippet(snippet);
            } else {
                topicMapper.updateBimSnippetEntity(topicPUT.getBimSnippet(), snippet);
            }
        }
        if(topicPUT.getDueDate() != null) {
            try {
                existingTopic.setDueDate(DateUtils.toInstant(topicPUT.getDueDate()));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid due_date format, expected ISO-8601");
            }
        }

        Instant updateEvent = Instant.now();
        existingTopic.setModifiedDate(updateEvent);
        existingTopic.setModifiedAuthor(securityContextService.getCurrentUserEmail());

        // Update TopicEventEntity
        if(!Objects.equals(beforeTitle, existingTopic.getTitle())) {
            createTopicEvent(existingTopic, TopicEventType.title_updated, existingTopic.getTitle(), updateEvent, existingTopic.getModifiedAuthor());
        }

        if (!Objects.equals(beforeDescription, existingTopic.getDescription())) {
            if (existingTopic.getDescription() == null || existingTopic.getDescription().isBlank()) {
                createTopicEvent(existingTopic, TopicEventType.description_removed, null, updateEvent, existingTopic.getModifiedAuthor());
            } else {
                createTopicEvent(existingTopic, TopicEventType.description_updated, existingTopic.getDescription(), updateEvent, existingTopic.getModifiedAuthor());
            }
        }

        if (!Objects.equals(beforeStatus, existingTopic.getTopicStatus())) {
            createTopicEvent(existingTopic, TopicEventType.status_updated, existingTopic.getTopicStatus(), updateEvent, existingTopic.getModifiedAuthor());
        }

        if (!Objects.equals(beforeType, existingTopic.getTopicType())) {
            createTopicEvent(existingTopic, TopicEventType.type_updated, existingTopic.getTopicType(), updateEvent, existingTopic.getModifiedAuthor());
        }

        if (!Objects.equals(beforePriority, existingTopic.getPriority())) {
            if (existingTopic.getPriority() == null || existingTopic.getPriority().isBlank()) {
                createTopicEvent(existingTopic, TopicEventType.priority_removed, null, updateEvent, existingTopic.getModifiedAuthor());
            } else {
                createTopicEvent(existingTopic, TopicEventType.priority_updated, existingTopic.getPriority(), updateEvent, existingTopic.getModifiedAuthor());
            }
        }

        if (!Objects.equals(beforeDueDate, existingTopic.getDueDate())) {
            if (existingTopic.getDueDate() == null) {
                createTopicEvent(existingTopic, TopicEventType.due_date_removed, null, updateEvent, existingTopic.getModifiedAuthor());
            } else {
                createTopicEvent(existingTopic, TopicEventType.due_date_updated, existingTopic.getDueDate().toString(), updateEvent, existingTopic.getModifiedAuthor());
            }
        }

        if (!Objects.equals(beforeAssignedTo, existingTopic.getAssignedTo())) {
            if (existingTopic.getAssignedTo() == null || existingTopic.getAssignedTo().isBlank()) {
                createTopicEvent(existingTopic, TopicEventType.assigned_to_removed, null, updateEvent, existingTopic.getModifiedAuthor());
            } else {
                createTopicEvent(existingTopic, TopicEventType.assigned_to_updated, existingTopic.getAssignedTo(), updateEvent, existingTopic.getModifiedAuthor());
            }
        }

        if (!Objects.equals(beforeStage, existingTopic.getStage())) {
            if (beforeStage == null && existingTopic.getStage() != null) {
                createTopicEvent(existingTopic, TopicEventType.stage_added, existingTopic.getStage(), updateEvent, existingTopic.getModifiedAuthor());
            } else if (beforeStage != null && existingTopic.getStage() == null) {
                createTopicEvent(existingTopic, TopicEventType.stage_removed, beforeStage, updateEvent, existingTopic.getModifiedAuthor());
            } else {
                createTopicEvent(existingTopic, TopicEventType.stage_updated, existingTopic.getStage(), updateEvent, existingTopic.getModifiedAuthor());
            }
        }

        if (topicPUT.getLabels() != null) {
            List<String> afterLabels = existingTopic.getLabels() == null ? List.of() : existingTopic.getLabels();

            Set<String> beforeSet = new HashSet<>(beforeLabels);
            Set<String> afterSet = new HashSet<>(afterLabels);

            for (String lbl : afterSet) {
                if (!beforeSet.contains(lbl)) {
                    createTopicEvent(existingTopic, TopicEventType.label_added, lbl, updateEvent, existingTopic.getModifiedAuthor());
                }
            }

            for (String lbl : beforeSet) {
                if (!afterSet.contains(lbl)) {
                    createTopicEvent(existingTopic, TopicEventType.label_removed, lbl, updateEvent, existingTopic.getModifiedAuthor());
                }
            }
        }

        return topicRepository.save(existingTopic);
    }

    //----------------- HELPER METHODS -----------------

    private <T> T validateValue(T target, List<T> validValues) {
        if (target == null) return null;
        if (!validValues.contains(target) && !validValues.isEmpty()) {
            throw new IllegalArgumentException("Invalid value: " + target);
        }
        return target;
    }

    private <T> List<T> validateList(List<T> targets, List<T> validList) {
        if(targets == null) return List.of();
        for (T target : targets) {
            if (!validList.contains(target) && !validList.isEmpty()) {
                throw new IllegalArgumentException("Invalid values: " + target);
            }
        }
        return targets;
    }

    private void createTopicEvent(
            TopicEntity topic,
            TopicEventType type,
            String value,
            Instant eventTime,
            String author
    ) {
        TopicEventEntity event = new TopicEventEntity();
        event.setProjectGuid(topic.getProject().getGuid());
        event.setTopicGuid(topic.getGuid());
        event.setAuthor(author);
        event.setEventType(type);
        event.setEventValue(value);
        event.setEventDate(eventTime);

        topicEventRepository.save(event);
    }

    private static final Map<String, String> TOPIC_FILTER_MAPPING = Map.ofEntries(
            Map.entry("creation_author", "creationAuthor"),
            Map.entry("modified_author", "modifiedAuthor"),
            Map.entry("assigned_to", "assignedTo"),
            Map.entry("stage", "stage"),
            Map.entry("topic_status", "topicStatus"),
            Map.entry("topic_type", "topicType"),
            Map.entry("priority", "priority"),
            Map.entry("creation_date", "creationDate"),
            Map.entry("modified_date", "modifiedDate")
    );

    private static final Map<String, String> TOPIC_ORDER_MAPPING = Map.of(
            "creation_date", "creationDate",
            "modified_date", "modifiedDate",
            "server_assigned_id", "serverAssignedId",
            "index", "index"
    );


    //----------------- RELATED TOPICS -----------------

    public List<String> getRelatedTopicGuids(String topicId, String projectId) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        return topic.getRelatedTopics();
    }

    public List<String> updateRelatedTopics(String topicId, String projectId, List<RelatedTopicPUT> relatedTopicPUT) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        topic.getRelatedTopics().clear();
        for (RelatedTopicPUT relatedTopic : relatedTopicPUT) {
            TopicEntity related = entityResolver.resolveTopic(projectId, relatedTopic.getRelatedTopicGuid());
            if (related.getId().equals(topic.getId())) {
                throw new IllegalArgumentException("A topic cannot be related to itself");
            }
            topic.getRelatedTopics().add(related.getGuid());
        }
        topicRepository.save(topic);
        return topic.getRelatedTopics();
    }


    //----------------- DOCUMENT REFERENCES -----------------

    public DocumentReferenceEntity createDocumentReference(String topicId, String projectId, DocumentReferencePOST documentReferencePOST) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);

        if (documentReferencePOST.getDocumentGuid() != null && documentReferencePOST.getUrl() != null) {
            throw new IllegalArgumentException("Either document_guid or url can be set, not both");
        }

        if (documentReferencePOST.getDocumentGuid() == null && documentReferencePOST.getUrl() == null) {
            throw new IllegalArgumentException("Either document_guid or url must be set");
        }

        DocumentReferenceEntity docRef = new DocumentReferenceEntity();
        String guid = documentReferencePOST.getGuid() != null ? documentReferencePOST.getGuid() : UUID.randomUUID().toString();
        if (entityResolver.resolveDocumentReference(guid, topicId) != null) {
            throw new ConflictException("Document Reference with GUID already exists in topic");
        }
        docRef.setGuid(guid);
        docRef.setTopic(topic);

        if (documentReferencePOST.getDocumentGuid() != null) {
            DocumentEntity doc = entityResolver.resolveDocument(documentReferencePOST.getDocumentGuid(), projectId);
            docRef.setDocument(doc);
        } else {
            docRef.setUrl(documentReferencePOST.getUrl());
        }
        docRef.setDescription(documentReferencePOST.getDescription());

        topic.getDocumentReferences().add(docRef);
        topicRepository.save(topic);
        return docRef;
    }

    public List<DocumentReferenceEntity> getDocumentReferences(String topicId, String projectId) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        return topic.getDocumentReferences();
    }

    public DocumentReferenceEntity updateDocumentReference(String topicId, String projectId, String documentReferenceId, DocumentReferencePUT documentReferencePUT) {
        DocumentReferenceEntity docRef = entityResolver.resolveDocumentReference(documentReferenceId, topicId);
        if (documentReferencePUT.getDocumentGuid() != null && documentReferencePUT.getUrl() != null) {
            throw new IllegalArgumentException("Either document_guid or url can be set, not both");
        }
        if (documentReferencePUT.getDocumentGuid() == null && documentReferencePUT.getUrl() == null) {
            throw new IllegalArgumentException("Either document_guid or url must be set");
        }

        docRef.setDocument(null);
        docRef.setUrl(null);
        if (documentReferencePUT.getDocumentGuid() != null) {
            DocumentEntity doc = entityResolver.resolveDocument(documentReferencePUT.getDocumentGuid(), projectId);
            docRef.setDocument(doc);
        } else {
            docRef.setUrl(documentReferencePUT.getUrl());
        }
        docRef.setDescription(documentReferencePUT.getDescription());
        return docRef;
    }


    //----------------- EVENTS -----------------

    public List<TopicEventEntity> getTopicEvents(String projectId, String $top, String $skip, String $filter, String $orderby) {
        entityResolver.resolveProject(projectId);
        int limit = ($top != null) ? Integer.parseInt($top) : 100;
        int offset = ($skip != null) ? Integer.parseInt($skip) : 0;
        Sort sort = ODataFilterOrderParser.parseOrderBy($orderby,"eventDate", Map.of("date", "eventDate"));
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, sort);

        Specification<TopicEventEntity> spec = (root, query, cb) -> cb.equal(root.get("projectGuid"), projectId);
        if($filter != null && !$filter.isBlank()) {
            spec = spec.and(ODataFilterOrderParser.getFilter($filter, TOPIC_EVENT_FILTER_MAPPING, Map.of("type", TopicEventType.class)));
        }
        Page<TopicEventEntity> events = topicEventRepository.findAll(spec, pageable);
        return events.getContent();
    }

    private static final Map<String, String> EVENT_FILTER_MAPPING = Map.of(
            "author", "author",
            "type", "eventType",
            "date", "eventDate"
    );

    private static Map<String, String> withTopicGuid(Map<String, String> base) {
        Map<String, String> map = new HashMap<>(base);
        map.put("topic_guid", "topicGuid");
        return Map.copyOf(map);
    }

    private static final Map<String, String> TOPIC_EVENT_FILTER_MAPPING =
            withTopicGuid(EVENT_FILTER_MAPPING);


    public List<TopicEventEntity> getTopicEventsByTopicId(String projectId, String topicId, String $top, String $skip, String $filter, String $orderby) {
        entityResolver.resolveTopic(projectId, topicId);
        int limit = ($top != null) ? Integer.parseInt($top) : 100;
        int offset = ($skip != null) ? Integer.parseInt($skip) : 0;
        Sort sort = ODataFilterOrderParser.parseOrderBy($orderby,"eventDate", Map.of("date", "eventDate"));
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, sort);

        Specification<TopicEventEntity> spec = (root, query, cb) -> cb.and(
                        cb.equal(root.get("projectGuid"), projectId),
                        cb.equal(root.get("topicGuid"), topicId));
        if($filter != null && !$filter.isBlank()) {
            spec = spec.and(ODataFilterOrderParser.getFilter($filter, EVENT_FILTER_MAPPING, Map.of("type", TopicEventType.class)));
        }
        Page<TopicEventEntity> events = topicEventRepository.findAll(spec, pageable);
        return events.getContent();
    }
}
