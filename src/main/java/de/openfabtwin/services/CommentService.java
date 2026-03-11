package de.openfabtwin.services;

import de.openfabtwin.entities.*;
import de.openfabtwin.generated.dto.CommentPOST;
import de.openfabtwin.generated.dto.CommentPUT;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.utils.ODataFilterOrderParser;
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
    private final EntityResolver entityResolver;
    private final SecurityContextService securityContextService;
    private final EventService eventService;

    public CommentEntity getById(String commentId, String topicId, String projectId) {
        return entityResolver.resolveComment(projectId, topicId, commentId);
    }

    public void delete(String commentId, String topicId, String projectId) {
        CommentEntity comment = entityResolver.resolveComment(projectId, topicId, commentId);
        commentRepository.delete(comment);
    }

    public CommentEntity create(String projectId, String topicId, CommentPOST commentPOST) {
        if (commentPOST.getComment() == null && commentPOST.getViewpointGuid() == null) {
            throw new IllegalArgumentException("Either comment text or viewpoint GUID must be provided");
        }
        
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        Instant createEventTime = Instant.now();

        CommentEntity comment = new CommentEntity();
        comment.setGuid(UUID.randomUUID().toString());
        comment.setDate(createEventTime);
        comment.setAuthor(securityContextService.getCurrentUserEmail());
        comment.setTopic(topic);
        if (commentPOST.getComment() != null) {
            comment.setComment(commentPOST.getComment());
        }
        if (commentPOST.getViewpointGuid() != null) {
            ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, commentPOST.getViewpointGuid());
            comment.setViewpoint(vp);
        }
        if(commentPOST.getReplyToCommentGuid() != null) {
            entityResolver.resolveComment(projectId, topicId, commentPOST.getReplyToCommentGuid());
            comment.setReplyToCommentGuid(commentPOST.getReplyToCommentGuid());
        }

        CommentEntity savedComment = commentRepository.save(comment);
        eventService.createCommentEvent(savedComment, CommentEventType.comment_created, null, createEventTime, savedComment.getAuthor());
        return savedComment;
    }

    public List<CommentEntity> getAll(String projectId, String topicId, String filter, String orderby) {
        entityResolver.resolveTopic(projectId, topicId);
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
        CommentEntity existingComment = entityResolver.resolveComment(projectId, topicId, commentId);
        if(commentPUT.getComment() == null && commentPUT.getViewpointGuid() == null) {
            throw new IllegalArgumentException("Either comment text or viewpoint GUID must be provided");
        }

        Instant updateTime = Instant.now();
        existingComment.setModifiedDate(updateTime);
        String updatedAuthor = securityContextService.getCurrentUserEmail();
        existingComment.setModifiedAuthor(updatedAuthor);

        if(commentPUT.getComment() != null) {
            existingComment.setComment(commentPUT.getComment());
            eventService.createCommentEvent(existingComment, CommentEventType.comment_text_updated, commentPUT.getComment(), updateTime, updatedAuthor);
        }

        ViewpointEntity beforeViewpoint = existingComment.getViewpoint();
        if(commentPUT.getViewpointGuid() != null) {
            ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, commentPUT.getViewpointGuid());
            existingComment.setViewpoint(vp);
            if (beforeViewpoint == null || !beforeViewpoint.getGuid().equals(vp.getGuid())) {
                eventService.createCommentEvent(existingComment, CommentEventType.viewpoint_updated, commentPUT.getViewpointGuid(), updateTime, updatedAuthor);
            }
        }
        if (commentPUT.getViewpointGuid() == null) {
            if (beforeViewpoint != null) {
                existingComment.setViewpoint(null);
                eventService.createCommentEvent(existingComment, CommentEventType.viewpoint_removed, null, updateTime, updatedAuthor);
            }
        }
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
