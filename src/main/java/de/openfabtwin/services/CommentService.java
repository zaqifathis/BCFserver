package de.openfabtwin.services;

import de.openfabtwin.entities.*;
import de.openfabtwin.generated.dto.CommentPOST;
import de.openfabtwin.generated.dto.CommentPUT;
import de.openfabtwin.repositories.CommentEventRepository;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.utils.ODataFilterOrderParser;
import de.openfabtwin.utils.OffsetBasedPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final EntityResolver entityResolver;
    private final CommentEventRepository commentEventRepository;

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
        comment.setAuthor("admin@localhost"); //TODO: set actual user
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
        createCommentEvent(savedComment, CommentEventType.comment_created, null, createEventTime, savedComment.getAuthor());
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
        String updatedAuthor = "admin@bcfserver"; //TODO: set actual user
        existingComment.setModifiedAuthor(updatedAuthor);

        if(commentPUT.getComment() != null) {
            existingComment.setComment(commentPUT.getComment());
            createCommentEvent(existingComment, CommentEventType.comment_text_updated, commentPUT.getComment(), updateTime, updatedAuthor);
        }

        ViewpointEntity beforeViewpoint = existingComment.getViewpoint();
        if(commentPUT.getViewpointGuid() != null) {
            ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, commentPUT.getViewpointGuid());
            existingComment.setViewpoint(vp);
            if (beforeViewpoint == null || !beforeViewpoint.getGuid().equals(vp.getGuid())) createCommentEvent(existingComment, CommentEventType.viewpoint_updated, commentPUT.getViewpointGuid(), updateTime, updatedAuthor);
        }
        if (commentPUT.getViewpointGuid() == null) {
            if (beforeViewpoint != null) {
                existingComment.setViewpoint(null);
                createCommentEvent(existingComment, CommentEventType.viewpoint_removed, null, updateTime, updatedAuthor);
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

    //----------------- EVENTS -----------------+

    private static final Map<String, String> EVENT_FILTER_MAPPING = Map.of(
            "author", "author",
            "type", "eventType",
            "date", "eventDate"
    );

    private static Map<String, String> withCommentTopicGuid(Map<String, String> base) {
        Map<String, String> map = new HashMap<>(base);
        map.put("topic_guid", "topicGuid");
        map.put("comment_guid", "commentGuid");
        return Map.copyOf(map);
    }

    private static final Map<String, String> COMMENT_EVENT_FILTER_MAPPING =
            withCommentTopicGuid(EVENT_FILTER_MAPPING);

    private void createCommentEvent(
            CommentEntity comment,
            CommentEventType type,
            String value,
            Instant eventTime,
            String author
    ) {
        CommentEventEntity event = new CommentEventEntity();
        event.setCommentGuid(comment.getGuid());
        event.setTopicGuid(comment.getTopic().getGuid());
        event.setProjectGuid(comment.getTopic().getProject().getGuid());
        event.setAuthor(author);
        event.setEventDate(eventTime);
        event.setEventType(type);
        event.setEventValue(value);
        commentEventRepository.save(event);
    }

    public List<CommentEventEntity> getCommentEvents(String projectId, String $top, String $skip, String $filter, String $orderby) {
        entityResolver.resolveProject(projectId);
        int limit = ($top != null) ? Integer.parseInt($top) : 100;
        int offset = ($skip != null) ? Integer.parseInt($skip) : 0;
        Sort sort = ODataFilterOrderParser.parseOrderBy($orderby,"eventDate", Map.of("date", "eventDate"));
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, sort);

        Specification<CommentEventEntity> spec = (root, query, cb) -> cb.equal(root.get("projectGuid"), projectId);
        if($filter != null && !$filter.isBlank()) {
            spec = spec.and(ODataFilterOrderParser.getFilter($filter, COMMENT_EVENT_FILTER_MAPPING, Map.of("type", CommentEventType.class)));
        }
        Page<CommentEventEntity> events = commentEventRepository.findAll(spec, pageable);
        return events.getContent();
    }

    public List<CommentEventEntity> getCommentEventsByCommentId(String projectId, String topicId, String commentId, String $top, String $skip, String $filter, String $orderby) {
        entityResolver.resolveComment(projectId, topicId, commentId);
        int limit = ($top != null) ? Integer.parseInt($top) : 100;
        int offset = ($skip != null) ? Integer.parseInt($skip) : 0;
        Sort sort = ODataFilterOrderParser.parseOrderBy($orderby,"eventDate", Map.of("date", "eventDate"));
        Pageable pageable = new OffsetBasedPageRequest(offset, limit, sort);

        Specification<CommentEventEntity> spec = (root, query, cb) -> cb.and(
                cb.equal(root.get("projectGuid"), projectId),
                cb.equal(root.get("topicGuid"), topicId),
                cb.equal(root.get("commentGuid"), commentId));
        if($filter != null && !$filter.isBlank()) {
            spec = spec.and(ODataFilterOrderParser.getFilter($filter, EVENT_FILTER_MAPPING, Map.of("type", CommentEventType.class)));
        }
        Page<CommentEventEntity> events = commentEventRepository.findAll(spec, pageable);
        return events.getContent();

    }
}
