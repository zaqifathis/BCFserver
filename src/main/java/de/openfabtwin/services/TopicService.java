package de.openfabtwin.services;

import de.openfabtwin.ExtensionXmlParser;
import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.generated.dto.TopicPUT;
import de.openfabtwin.generated.extensions.Extensions;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.DateUtils;
import de.openfabtwin.utils.ODataFilterOrderParser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final EntityManager entityManager;
    private final ExtensionXmlParser extensionXmlParser;
    private final EntityResolver entityResolver;

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
        topic.setIndex(topicPOST.getIndex());
        topic.setDescription(topicPOST.getDescription());
        TemporalAccessor ta = DateUtils.parseBcfDateTime(topicPOST.getDueDate());
        topic.setDueDate(DateUtils.toInstant(ta));
        topic.setCreationAuthor("admin@localhost"); // TODO: set actual user
        topic.setCreationDate(Instant.now());

        ExtensionEntity extension = entityResolver.resolveProjectExtension(projectId);
        Extensions xmlExtensions = extensionXmlParser.parse(extension.getExtensionXml());
        topic.setTopicType(validateValue(topicPOST.getTopicType(), xmlExtensions.getTopicTypes() != null ? xmlExtensions.getTopicTypes().getTopicType() : List.of()));
        topic.setTopicStatus(validateValue(topicPOST.getTopicStatus(), xmlExtensions.getTopicStatuses() != null ? xmlExtensions.getTopicStatuses().getTopicStatus() : List.of()));
        topic.setPriority(validateValue(topicPOST.getPriority(), xmlExtensions.getPriorities() != null ? xmlExtensions.getPriorities().getPriority() : List.of()));
        topic.setLabels(validateList(topicPOST.getLabels(), xmlExtensions.getTopicLabels() != null ? xmlExtensions.getTopicLabels().getTopicLabel() : List.of()));
        topic.setAssignedTo(validateValue(topicPOST.getAssignedTo(), xmlExtensions.getUsers() != null ? xmlExtensions.getUsers().getUser() : List.of()));
        topic.setStage(validateValue(topicPOST.getStage(), xmlExtensions.getStages() != null ? xmlExtensions.getStages().getStage() : List.of()));

        if(topicPOST.getBimSnippet() != null) {
            BimSnippetEntity snippetEntity = topicMapper.mapBimSnippetEntity(topicPOST.getBimSnippet(), topic);
            topic.setBimSnippet(snippetEntity);
        }
        topicRepository.save(topic);
        entityManager.flush();
        topic.setServerAssignedId("TOPIC_" + topic.getId());
        return topicRepository.save(topic);
    }

    public void delete(String topicId, String projectId) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        topicRepository.delete(topic);
    }

    public TopicEntity getById(String topicId, String projectId) {
        return entityResolver.resolveTopic(projectId, topicId);
    }

    public List<TopicEntity> getAll(String projectId, String filter, String orderby, String top, String skip) {
        entityResolver.resolveProject(projectId);
        int limit = (top != null) ? Integer.parseInt(top) : 100;
        int offset = (skip != null) ? Integer.parseInt(skip) : 0;
        int page = offset / limit;
        Sort sort = ODataFilterOrderParser.parseOrderBy(orderby,"creationDate", TOPIC_ORDER_MAPPING);
        Pageable pageable = PageRequest.of(page, limit, sort);

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

        ExtensionEntity extension = entityResolver.resolveProjectExtension(projectId);
        Extensions xmlExtensions = extensionXmlParser.parse(extension.getExtensionXml());

        existingTopic.setTitle(topicPUT.getTitle());
        if(topicPUT.getTopicType() != null) {
            existingTopic.setTopicType(validateValue(topicPUT.getTopicType(), xmlExtensions.getTopicTypes() != null ? xmlExtensions.getTopicTypes().getTopicType() : List.of()));
        }
        if(topicPUT.getTopicStatus() != null) {
            existingTopic.setTopicStatus(validateValue(topicPUT.getTopicStatus(), xmlExtensions.getTopicStatuses() != null ? xmlExtensions.getTopicStatuses().getTopicStatus() : List.of()));
        }
        if(topicPUT.getReferenceLinks() != null) {
            existingTopic.getReferenceLinks().clear();
            existingTopic.getReferenceLinks().addAll(topicPUT.getReferenceLinks());
        }
        if (topicPUT.getPriority() != null) {
            existingTopic.setPriority(validateValue(topicPUT.getPriority(), xmlExtensions.getPriorities() != null ? xmlExtensions.getPriorities().getPriority() : List.of()));
        }
        if(topicPUT.getIndex() != null) {
            existingTopic.setIndex(topicPUT.getIndex());
        }
        if(topicPUT.getLabels() != null) {
            List<String> labels = validateList(topicPUT.getLabels(), xmlExtensions.getTopicLabels() != null ? xmlExtensions.getTopicLabels().getTopicLabel() : List.of());
            existingTopic.getLabels().clear();
            existingTopic.getLabels().addAll(labels);
        }
        if(topicPUT.getAssignedTo() != null) {
            existingTopic.setAssignedTo(validateValue(topicPUT.getAssignedTo(), xmlExtensions.getUsers() != null ? xmlExtensions.getUsers().getUser() : List.of()));
        }
        if(topicPUT.getStage() != null) {
            existingTopic.setStage(validateValue(topicPUT.getStage(), xmlExtensions.getStages() != null ? xmlExtensions.getStages().getStage() : List.of()));
        }
        if(topicPUT.getDescription() != null) {
            existingTopic.setDescription(topicPUT.getDescription());
        }
        if(topicPUT.getBimSnippet() != null) {
            BimSnippetEntity snippet = existingTopic.getBimSnippet();

            if (snippet == null) {
                snippet = new BimSnippetEntity();
                snippet.addTopic(existingTopic);
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
                TemporalAccessor ta = DateUtils.parseBcfDateTime(topicPUT.getDueDate());
                existingTopic.setDueDate(DateUtils.toInstant(ta));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid due_date format, expected ISO-8601");
            }
        }
        return topicRepository.save(existingTopic);
    }

    //----------------- HELPER METHODS -----------------

    private <T> T validateValue(T target, List<T> validValues) {
        if (target == null) {
            return null;
        }
        if (!validValues.contains(target) && !validValues.isEmpty()) {
            throw new IllegalArgumentException("Invalid value: " + target);
        }
        return target;
    }

    private <T> List<T> validateList(List<T> targets, List<T> validList) {
        if(targets == null) {
            return List.of();
        }
        for (T target : targets) {
            if (!validList.contains(target) && !validList.isEmpty()) {
                throw new IllegalArgumentException("Invalid values: " + target);
            }
        }
        return targets;
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
}
