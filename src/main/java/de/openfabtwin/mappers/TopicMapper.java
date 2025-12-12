package de.openfabtwin.mappers;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.generated.dto.BimSnippet;
import de.openfabtwin.generated.dto.TopicGET;
import de.openfabtwin.generated.dto.TopicGETAuthorization;
import de.openfabtwin.repositories.ExtensionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    private final ExtensionRepository extensionRepository;

    public TopicMapper(ExtensionRepository extensionRepository) {
        this.extensionRepository = extensionRepository;
    }

    public TopicGET toDto(String projectId, TopicEntity created) {
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
        // TODO: user permissions for topic actions
        dto.setAuthorization(getTopicAuthorization(projectId));

        return dto;
    }

    private TopicGETAuthorization getTopicAuthorization(String projectId) {
        var authorization = new TopicGETAuthorization();
        var extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));

        var topicActions = extension.getTopicActions().stream()
                .map(actionEnum -> TopicGETAuthorization.TopicActionsEnum.fromValue(actionEnum.getValue()))
                .toList();
        authorization.setTopicActions(topicActions);
        return authorization;
    }

    public BimSnippetEntity mapBimSnippetEntity(@Valid BimSnippet bimSnippet, TopicEntity topic) {
        BimSnippetEntity entity = new BimSnippetEntity();
        entity.setSnippetType(bimSnippet.getSnippetType());
        entity.setIsExternal(Boolean.parseBoolean(bimSnippet.getIsExternal()));
        entity.setReference(bimSnippet.getReference());
        entity.setReferenceSchema(bimSnippet.getReferenceSchema());
        entity.setTopic(topic);
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
}
