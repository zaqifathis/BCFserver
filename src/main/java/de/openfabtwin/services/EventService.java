package de.openfabtwin.services;

import de.openfabtwin.entities.*;
import de.openfabtwin.repositories.CommentEventRepository;
import de.openfabtwin.repositories.TopicEventRepository;
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

@Service
@RequiredArgsConstructor
public class EventService {

    private final EntityResolver entityResolver;
    private final CommentEventRepository commentEventRepository;
    private final TopicEventRepository topicEventRepository;

    private static final Map<String, String> EVENT_FILTER_MAPPING = Map.of(
            "author", "author",
            "type", "eventType",
            "date", "eventDate"
    );

    //-----------------  TOPIC EVENTS -----------------+

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

    public void generateTopicEvent(
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

    public void generateTopicEvent(TopicEntity topic) {
        createEvent(topic, TopicEventType.topic_created, null, false);
        createEvent(topic, TopicEventType.title_updated, topic.getTitle(), false);
        if(topic.getDescription() != null) createEvent(topic, TopicEventType.description_updated, topic.getDescription(), false);
        if(topic.getTopicStatus() != null) createEvent(topic, TopicEventType.status_updated, topic.getTopicStatus(), false);
        if(topic.getTopicType() != null) createEvent(topic, TopicEventType.type_updated, topic.getTopicType(), false);
        if(topic.getStage() != null) createEvent(topic, TopicEventType.stage_added, topic.getStage(), false);
        if(topic.getPriority() != null) createEvent(topic, TopicEventType.priority_updated, topic.getPriority(), false);
        if(topic.getDueDate() != null) createEvent(topic,TopicEventType.due_date_updated, topic.getDueDate().toString(), false);
        if(topic.getAssignedTo() != null) createEvent(topic, TopicEventType.assigned_to_updated,topic.getAssignedTo(), false);
        if(topic.getLabels() != null) {
            for(String label : topic.getLabels()) {
                createEvent(topic, TopicEventType.label_added, label, false);
            }
        }
    }

    public void createEvent(TopicEntity topic, TopicEventType event, String value, boolean isUpdated) {
        TopicEventEntity entity = new TopicEventEntity();
        entity.setProjectGuid(topic.getProject().getGuid());
        entity.setTopicGuid(topic.getGuid());
        entity.setAuthor(isUpdated? topic.getModifiedAuthor(): topic.getCreationAuthor());
        entity.setEventDate(isUpdated? topic.getModifiedDate() : topic.getCreationDate());
        entity.setEventType(event);
        entity.setEventValue(value);
        topicEventRepository.save(entity);
    }

    //-----------------  COMMENT EVENTS -----------------+

    private static Map<String, String> withCommentTopicGuid(Map<String, String> base) {
        Map<String, String> map = new HashMap<>(base);
        map.put("topic_guid", "topicGuid");
        map.put("comment_guid", "commentGuid");
        return Map.copyOf(map);
    }

    private static final Map<String, String> COMMENT_EVENT_FILTER_MAPPING =
            withCommentTopicGuid(EVENT_FILTER_MAPPING);

    public void createCommentEvent(
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
