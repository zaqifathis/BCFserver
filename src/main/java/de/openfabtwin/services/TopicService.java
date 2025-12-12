package de.openfabtwin.services;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.generated.dto.BimSnippet;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.DateUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
            throw new IllegalArgumentException("Project does not have permission to create topics");
        }
        // TODO:check user permissions

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
