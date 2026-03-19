package de.openfabtwin.mappers;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.DocumentReferenceEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.TopicEventEntity;
import de.openfabtwin.generated.dto.*;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.utils.DateUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TopicMapper {

    private final ExtensionRepository extensionRepository;

    public TopicMapper(ExtensionRepository extensionRepository) {
        this.extensionRepository = extensionRepository;
    }

    public TopicGET toDto(TopicEntity created) {
        var dto = new TopicGET();
        dto.setGuid(created.getGuid());
        dto.setServerAssigendId(created.getServerAssignedId());
        dto.setTopicType(created.getTopicType());
        dto.setTopicStatus(created.getTopicStatus());
        dto.setReferenceLinks(created.getReferenceLinks());
        dto.setTitle(created.getTitle());
        dto.setPriority(created.getPriority());
        dto.setIndex(created.getIndex());
        dto.setLabels(created.getLabels());
        dto.setCreationDate(created.getCreationDate() != null ? created.getCreationDate().toString() : null);
        dto.setCreationAuthor(created.getCreationAuthor());
        dto.setModifiedDate(created.getModifiedDate() != null ? created.getModifiedDate().toString() : null);
        dto.setModifiedAuthor(created.getModifiedAuthor());
        dto.setAssignedTo(created.getAssignedTo());
        dto.setStage(created.getStage());
        dto.setDescription(created.getDescription());
        dto.setBimSnippet(mapBimSnippetDto(created.getBimSnippet()));
        dto.setDueDate(created.getDueDate() != null ? created.getDueDate().toString() : null);
        return dto;
    }


    public BimSnippetEntity mapBimSnippetEntity(BimSnippet bimSnippet, TopicEntity topic) {
        if(bimSnippet.getSnippetType() == null || bimSnippet.getIsExternal() == null ||
                bimSnippet.getReference() == null || bimSnippet.getReferenceSchema() == null) {
            throw new IllegalArgumentException("All BimSnippet fields are required");
        }
        if (!"true".equalsIgnoreCase(bimSnippet.getIsExternal())
                && !"false".equalsIgnoreCase(bimSnippet.getIsExternal())) {
            throw new IllegalArgumentException("is_external must be 'true' or 'false'");
        }
        BimSnippetEntity entity = new BimSnippetEntity();
        entity.setSnippetType(bimSnippet.getSnippetType());
        entity.setIsExternal(Boolean.parseBoolean(bimSnippet.getIsExternal()));
        entity.setReference(bimSnippet.getReference());
        entity.setReferenceSchema(bimSnippet.getReferenceSchema());
        entity.getTopics().add(topic);
        topic.setBimSnippet(entity);
        return entity;
    }

    private BimSnippet mapBimSnippetDto(BimSnippetEntity entity) {
        if (entity == null) {
            return null;
        }
        BimSnippet dto = new BimSnippet();
        dto.setSnippetType(entity.getSnippetType());
        dto.setIsExternal(String.valueOf(entity.getIsExternal()));
        dto.setReference(entity.getReference());
        dto.setReferenceSchema(entity.getReferenceSchema());
        return dto;
    }

    public void updateBimSnippetEntity(BimSnippet bimSnippet, BimSnippetEntity snippet) {
        if(bimSnippet.getSnippetType() == null || bimSnippet.getIsExternal() == null ||
           bimSnippet.getReference() == null || bimSnippet.getReferenceSchema() == null) {
            throw new IllegalArgumentException("All BimSnippet fields are required");
        }
        if (!"true".equalsIgnoreCase(bimSnippet.getIsExternal())
                && !"false".equalsIgnoreCase(bimSnippet.getIsExternal())) {
            throw new IllegalArgumentException("is_external must be 'true' or 'false'");
        }
        snippet.setSnippetType(bimSnippet.getSnippetType());
        snippet.setIsExternal(Boolean.parseBoolean(bimSnippet.getIsExternal()));
        snippet.setReference(bimSnippet.getReference());
        snippet.setReferenceSchema(bimSnippet.getReferenceSchema());
    }

    public RelatedTopicGET toRelatedTopicDto(String s) {
        RelatedTopicGET dto = new RelatedTopicGET();
        dto.setRelatedTopicGuid(s);
        return dto;
    }

    public DocumentReferenceGET toDocumentReferenceDto(DocumentReferenceEntity created) {
        DocumentReferenceGET dto = new DocumentReferenceGET();
        dto.setGuid(created.getGuid());
        dto.setDocumentGuid(created.getDocument().getGuid());
        dto.setUrl(created.getUrl());
        dto.setDescription(created.getDescription());
        return dto;
    }

    public List<TopicEventGET> toGroupedEventDto(List<TopicEventEntity> events) {
        Map<TopicEventGroupKey, List<TopicEventEntity>> grouped =
                events.stream()
                        .collect(Collectors.groupingBy(
                                e -> new TopicEventGroupKey(
                                        e.getTopicGuid(),
                                        e.getAuthor(),
                                        e.getEventDate()
                                )
                        ));

        return grouped.entrySet().stream()
                .map(entry -> toEventDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(TopicEventGET::getDate))
                .toList();
    }


    private TopicEventGET toEventDto(
            TopicEventGroupKey key,
            List<TopicEventEntity> group
    ) {
        TopicEventGET dto = new TopicEventGET();
        dto.setTopicGuid(key.topicGuid());
        dto.setAuthor(key.author());
        dto.setDate(DateUtils.toString(key.eventDate()));

        List<EventAction> actions = group.stream()
                .map(this::toAction)
                .toList();

        dto.setActions(actions);
        return dto;
    }

    private EventAction toAction(TopicEventEntity event) {
        EventAction action = new EventAction();
        if(event.getEventType().name().equals("title_updated")) {
            action.setType(event.getEventType().name());
            action.setValue(limitText(event.getEventValue(), 128));
        }
        else if(event.getEventType().name().equals("description_updated") || event.getEventType().name().equals("description_updated")) {
            action.setType(event.getEventType().name());
            action.setValue(limitText(event.getEventValue(), 1024));
        } else {
            action.setType(event.getEventType().name());
            action.setValue(event.getEventValue());
        }
        return action;
    }

    private String limitText(String text, int limit) {
        return text.substring(0, Math.min(text.length(), limit));
    }
}
