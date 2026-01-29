package de.openfabtwin.mappers;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.entities.CommentEventEntity;
import de.openfabtwin.generated.dto.CommentEventGET;
import de.openfabtwin.generated.dto.CommentGET;
import de.openfabtwin.generated.dto.EventAction;
import de.openfabtwin.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public CommentGET toDto(CommentEntity comment, String topicId) {
        CommentGET dto = new CommentGET();
        dto.setGuid(comment.getGuid());
        dto.setTopicGuid(topicId);
        dto.setDate(comment.getDate().toString());
        dto.setAuthor(comment.getAuthor());
        dto.setComment(comment.getComment());
        dto.setViewpointGuid(comment.getViewpoint() != null ? comment.getViewpoint().getGuid() : null);
        dto.setModifiedDate(comment.getModifiedDate() != null ? comment.getModifiedDate().toString() : null);
        dto.setModifiedAuthor(comment.getModifiedAuthor() != null ? comment.getModifiedAuthor() : null);
        dto.setReplyToCommentGuid(comment.getReplyToCommentGuid() != null ? comment.getReplyToCommentGuid() : null);

        return dto;
    }

    public List<CommentEventGET> toGroupedEventDto(List<CommentEventEntity> events) {
        Map<CommentEventGroupKey, List<CommentEventEntity>> grouped =
                events.stream()
                        .collect(Collectors.groupingBy(
                                e -> new CommentEventGroupKey(
                                        e.getCommentGuid(),
                                        e.getTopicGuid(),
                                        e.getAuthor(),
                                        e.getEventDate()
                                )
                        ));

        return grouped.entrySet().stream()
                .map(entry -> toEventDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CommentEventGET::getDate))
                .toList();
    }

    private CommentEventGET toEventDto(CommentEventGroupKey key, List<CommentEventEntity> group) {
        CommentEventGET dto = new CommentEventGET();
        dto.setCommentGuid(key.commentGuid());
        dto.setTopicGuid(key.topicGuid());
        dto.setDate(DateUtils.toString(key.eventDate()));
        dto.setAuthor(key.author());

        List<EventAction> actions = group.stream()
                .map(this::toAction)
                .toList();

        dto.setActions(actions);
        return dto;
    }

    private EventAction toAction(CommentEventEntity event) {
        EventAction action = new EventAction();
        action.setType(event.getEventType().name());
        action.setValue(event.getEventValue());
        return action;
    }
}
