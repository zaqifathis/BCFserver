package de.openfabtwin.controllers;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.generated.api.CommentsApi;
import de.openfabtwin.generated.dto.CommentGET;
import de.openfabtwin.generated.dto.CommentPOST;
import de.openfabtwin.generated.dto.CommentPUT;
import de.openfabtwin.mappers.CommentMapper;
import de.openfabtwin.services.CommentService;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController implements CommentsApi {

    private final BcfProperties props;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<CommentGET> createComment(String version, String projectId, String topicId, CommentPOST commentPOST) {
        props.validateVersion(version);
        CommentEntity created = commentService.create(projectId, topicId, commentPOST);
        return ResponseEntity.status(201).body(commentMapper.toDto(created, topicId));
    }

    @Override
    public ResponseEntity<Void> deleteComment(String version, String projectId, String topicId, String commentId) {
        props.validateVersion(version);
        commentService.delete(commentId, topicId, projectId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<CommentGET> getCommentById(String version, String projectId, String topicId, String commentId) {
        props.validateVersion(version);
        CommentEntity comment = commentService.getById(commentId, topicId, projectId);
        return ResponseEntity.ok(commentMapper.toDto(comment, topicId));
    }

    @Override
    public ResponseEntity<List<CommentGET>> getTopicComment(String version, String projectId, String topicId, String $filter, String $orderby) {
        props.validateVersion(version);
        List<CommentGET> comments = commentService.getAll(projectId, topicId, $filter, $orderby)
                .stream()
                .map(comment -> commentMapper.toDto(comment, topicId))
                .toList();
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<CommentGET> updateComment(String version, String projectId, String topicId, String commentId, CommentPUT commentPUT) {
        props.validateVersion(version);
        CommentEntity updated = commentService.update(commentId, topicId, projectId, commentPUT);
        return ResponseEntity.ok(commentMapper.toDto(updated, topicId));
    }
}
