package de.openfabtwin.services;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.generated.dto.CommentPOST;
import de.openfabtwin.generated.dto.CommentPUT;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.ODataFilterOrderParser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ExtensionRepository extensionRepository;
    private final TopicRepository topicRepository;

    public CommentEntity getById(String commentId, String topicId, String projectId) {
        return commentRepository.findByGuidAndTopic_GuidAndTopic_Project_Guid(commentId, topicId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }

    public void delete(String commentId, String topicId, String projectId) {
        CommentEntity comment = getById(commentId, topicId, projectId);
        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Extension not found for project: " + projectId));
        if (extension.getCommentActions().contains(ExtensionsGET.CommentActionsEnum.DELETE)) {
            throw new IllegalArgumentException("User does not have permission to delete topics");
        }
        commentRepository.delete(comment);
    }

    public CommentEntity create(String projectId, String topicId, CommentPOST commentPOST) {
        TopicEntity topic = topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        if (commentPOST.getComment() == null && commentPOST.getViewpointGuid() == null) {
            throw new IllegalArgumentException("Either comment text or viewpoint GUID must be provided");
        }

        CommentEntity comment = new CommentEntity();
        comment.setGuid(UUID.randomUUID().toString());
        comment.setDate(Instant.now());
        comment.setAuthor("admin@bcfserver"); //TODO: set actual user
        comment.setTopic(topic);
        comment.setComment(commentPOST.getComment());
        comment.setViewpointGuid(commentPOST.getViewpointGuid());

        return commentRepository.save(comment);
    }

    public List<CommentEntity> getAll(String projectId, String topicId, String filter, String orderby) {
        Sort sort = ODataFilterOrderParser.parseOrderBy(orderby, "date", COMMENT_ORDER_MAPPING);
        Specification<CommentEntity> spec = Specification.<CommentEntity>unrestricted().and(
                (root, query, cb) -> cb.equal(root.get("topic").get("guid"), topicId)
        ).and(
                (root, query, cb) -> cb.equal(root.get("topic").get("project").get("guid"), projectId)
        );
        if (filter != null && !filter.isBlank()) {
            Specification<CommentEntity> filterSpec = ODataFilterOrderParser.getFilter(filter, COMMENT_FILTER_MAPPING);
            spec = spec.and(filterSpec);
        }
        return commentRepository.findAll(spec, sort);
    }

    public CommentEntity update(String commentId, String topicId, String projectId, CommentPUT commentPUT) {
        CommentEntity existingComment = commentRepository.findByGuidAndTopic_GuidAndTopic_Project_Guid(commentId, topicId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Extension not found for project: " + projectId));
        if (!extension.getCommentActions().contains(ExtensionsGET.CommentActionsEnum.UPDATE)) {
            throw new IllegalArgumentException("User does not have permission to update comment");
        }

        if(commentPUT.getComment() == null && commentPUT.getViewpointGuid() == null) {
            throw new IllegalArgumentException("Either comment text or viewpoint GUID must be provided");
        }

        if(commentPUT.getComment() != null) {
            existingComment.setComment(commentPUT.getComment());
        }
        if(commentPUT.getViewpointGuid() != null) {
            existingComment.setViewpointGuid(commentPUT.getViewpointGuid());
        }
        existingComment.setModifiedDate(Instant.now());
        existingComment.setModifiedAuthor("admin@bcfserver"); //TODO: set actual user

        return commentRepository.save(existingComment);
    }

    private static final Map<String, String> COMMENT_ORDER_MAPPING = Map.of(
            "date", "date"
    );

    private static final Map<String, String> COMMENT_FILTER_MAPPING = Map.of(
            "author", "author",
            "date", "date"
    );
}
