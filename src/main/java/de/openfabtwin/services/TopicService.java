package de.openfabtwin.services;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.generated.dto.TopicPUT;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ExtensionRepository extensionRepository;
    private final ProjectRepository projectRepository;
    private final TopicMapper topicMapper;

    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));
        if (!extension.getProjectActions().contains(ExtensionsGET.ProjectActionsEnum.CREATE_TOPIC)) {
            throw new IllegalArgumentException("User does not have permission to create topics");
        }
        if(topicPOST.getTitle() == null || topicPOST.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        var topic = new TopicEntity();
        topic.setGuid(UUID.randomUUID().toString());
        topic.setTopicType(validateValue(topicPOST.getTopicType(), extension.getTopicType()));
        topic.setTopicStatus(validateValue(topicPOST.getTopicStatus(), extension.getTopicStatus()));
        topic.setReferenceLinks(topicPOST.getReferenceLinks());
        topic.setTitle(topicPOST.getTitle());
        topic.setPriority(validateValue(topicPOST.getPriority(), extension.getPriority()));
        topic.setIndex(topicPOST.getIndex());
        topic.setLabels(validateList(topicPOST.getLabels(), extension.getTopicLabel()));
        topic.setAssignedTo(validateValue(topicPOST.getAssignedTo(), extension.getUsers()));
        topic.setStage(validateValue(topicPOST.getStage(), extension.getStage()));
        topic.setDescription(topicPOST.getDescription());
        topic.setDueDate(DateUtils.toInstant(topicPOST.getDueDate()));
        topic.setCreationAuthor("admin@bcfserver"); // TODO: set actual user
        topic.setCreationDate(Instant.now());
        topic.setProject(projectRepository.findByGuid(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId)));
        if(topicPOST.getBimSnippet() != null) {
            BimSnippetEntity snippetEntity = topicMapper.mapBimSnippetEntity(topicPOST.getBimSnippet(), topic);
            topic.setBimSnippet(snippetEntity);
        }
        return topicRepository.save(topic);
    }

    public void delete(String topicId, String projectId) {
        TopicEntity topic = topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Could not delete topic: " + topicId + " not found in project: " + projectId));
        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));

        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.DELETE)) {
            throw new IllegalArgumentException("User does not have permission to delete topics");
        }
        topicRepository.delete(topic);
    }

    public TopicEntity getById(String topicId, String projectId) {
        return topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Topic: " + topicId + " not found in project: " + projectId));
    }

    public TopicEntity update(String topicId, String projectId, TopicPUT topicPUT) {
        TopicEntity existingTopic = topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Could not update topic: " + topicId + " not found in project: " + projectId));

        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));
        if (!extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE)) {
            throw new IllegalArgumentException("User does not have permission to update topics");
        }

        if (topicPUT.getTitle() == null) {
            throw new IllegalArgumentException("Title is required");
        }
        existingTopic.setTitle(topicPUT.getTitle());
        if(topicPUT.getTopicType() != null) {
            existingTopic.setTopicType(validateValue(topicPUT.getTopicType(), extension.getTopicType()));
        }
        if(topicPUT.getTopicStatus() != null) {
            existingTopic.setTopicStatus(validateValue(topicPUT.getTopicStatus(), extension.getTopicStatus()));
        }
        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE_DOCUMENT_REFERENCES) && topicPUT.getReferenceLinks() != null) {
            existingTopic.getReferenceLinks().clear();
            existingTopic.getReferenceLinks().addAll(topicPUT.getReferenceLinks());
        }
        if (topicPUT.getPriority() != null) {
            existingTopic.setPriority(validateValue(topicPUT.getPriority(), extension.getPriority()));
        }
        if(topicPUT.getIndex() != null) {
            existingTopic.setIndex(topicPUT.getIndex());
        }
        if(topicPUT.getLabels() != null) {
            List<String> labels = validateList(topicPUT.getLabels(), extension.getTopicLabel());
            existingTopic.getLabels().clear();
            existingTopic.getLabels().addAll(labels);
        }
        if(topicPUT.getAssignedTo() != null) {
            existingTopic.setAssignedTo(validateValue(topicPUT.getAssignedTo(), extension.getUsers()));
        }
        if(topicPUT.getStage() != null) {
            existingTopic.setStage(validateValue(topicPUT.getStage(), extension.getStage()));
        }
        if(topicPUT.getDescription() != null) {
            existingTopic.setDescription(topicPUT.getDescription());
        }
        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE_BIM_SNIPPET) && topicPUT.getBimSnippet() != null) {
            BimSnippetEntity snippet = existingTopic.getBimSnippet();

            if (snippet == null) {
                snippet = new BimSnippetEntity();
                snippet.setTopic(existingTopic);
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



}
