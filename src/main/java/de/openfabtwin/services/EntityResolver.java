package de.openfabtwin.services;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityResolver {

    private final ProjectRepository projectRepository;
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final ExtensionRepository extensionRepository;

    public ProjectEntity resolveProject(String projectId) {
        return projectRepository.findByGuid(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectId));
    }

    public TopicEntity resolveTopic(String projectId, String topicId) {
        resolveProject(projectId);
        return topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found: " + topicId + " in project " + projectId)
                );
    }

    public CommentEntity resolveComment(String projectId, String topicId, String commentId) {
        resolveTopic(projectId, topicId);

        return commentRepository.findByGuidAndTopic_GuidAndTopic_Project_Guid(commentId, topicId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found: " + commentId + " in topic " + topicId + " and project " + projectId));
    }

    public ExtensionEntity resolveProjectExtension(String projectId) {
        resolveProject(projectId);
        return extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Extension not found for project " + projectId));
    }

}
