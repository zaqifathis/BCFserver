package de.openfabtwin.controllers;

import de.openfabtwin.entities.CommentEventEntity;
import de.openfabtwin.entities.TopicEventEntity;
import de.openfabtwin.generated.api.EventsApi;
import de.openfabtwin.generated.dto.CommentEventGET;
import de.openfabtwin.generated.dto.TopicEventGET;
import de.openfabtwin.mappers.CommentMapper;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.services.CommentService;
import de.openfabtwin.services.TopicService;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController implements EventsApi {

    private final BcfProperties props;
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<List<CommentEventGET>> getCommentEvent(String version, String projectId, String topicId, String commentId, String $top, String $skip, String $filter, String $orderby) {
        props.validateVersion(version);
        List<CommentEventEntity> entity = commentService.getCommentEventsByCommentId(projectId, topicId, commentId, $top, $skip, $filter, $orderby);
        List<CommentEventGET> events = commentMapper.toGroupedEventDto(entity);
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<List<CommentEventGET>> getCommentEvents(String version, String projectId, String $top, String $skip, String $filter, String $orderby) {
        props.validateVersion(version);
        List<CommentEventEntity> entity = commentService.getCommentEvents(projectId, $top, $skip, $filter, $orderby);
        List<CommentEventGET> events = commentMapper.toGroupedEventDto(entity);
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<List<TopicEventGET>> getEvents(String version, String projectId, String $top, String $skip, String $filter, String $orderby) {
        props.validateVersion(version);
        List<TopicEventEntity> entity = topicService.getTopicEvents(projectId, $top, $skip, $filter, $orderby);
        List<TopicEventGET> events = topicMapper.toGroupedEventDto(entity);
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<List<TopicEventGET>> getTopicEvents(String version, String projectId, String topicId, String $top, String $skip, String $filter, String $orderby) {
        props.validateVersion(version);
        List<TopicEventEntity> entity = topicService.getTopicEventsByTopicId(projectId, topicId, $top, $skip, $filter, $orderby);
        List<TopicEventGET> events = topicMapper.toGroupedEventDto(entity);
        return ResponseEntity.ok(events);
    }
}
