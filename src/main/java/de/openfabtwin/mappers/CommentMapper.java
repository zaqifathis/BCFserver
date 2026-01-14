package de.openfabtwin.mappers;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.generated.dto.CommentGET;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentGET toDto(CommentEntity comment, String topicId) {
        CommentGET dto = new CommentGET();
        dto.setGuid(comment.getGuid());
        dto.setTopicGuid(topicId);
        dto.setDate(comment.getDate().toString());
        dto.setAuthor(comment.getAuthor());
        dto.setComment(comment.getComment());
        dto.setViewpointGuid(comment.getViewpointGuid());
        dto.setModifiedDate(comment.getModifiedDate() != null ? comment.getModifiedDate().toString() : null);
        dto.setModifiedAuthor(comment.getModifiedAuthor() != null ? comment.getModifiedAuthor() : null);
        dto.setReplyToCommentGuid(comment.getReplyToCommentGuid() != null ? comment.getReplyToCommentGuid() : null);

        return dto;
    }
}
