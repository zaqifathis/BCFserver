package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.generated.api.CommentsApi;
import de.openfabtwin.generated.dto.CommentGET;
import de.openfabtwin.generated.dto.CommentPOST;
import de.openfabtwin.generated.dto.CommentPUT;
import de.openfabtwin.mappers.CommentMapper;
import de.openfabtwin.services.AuthorizationAssembler;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.CommentService;
import de.openfabtwin.services.SecurityContextService;
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
    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final AuthorizationAssembler authorizationAssembler;

    @Override
    public ResponseEntity<CommentGET> createComment(String version, String projectId, String topicId, CommentPOST commentPOST) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.can(role, Actions.Topic.CREATE_COMMENT);
        CommentEntity created = commentService.create(projectId, topicId, commentPOST);
        CommentGET dto = commentMapper.toDto(created, topicId);
        dto.setAuthorization(authorizationAssembler.commentAuthorization(role));
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteComment(String version, String projectId, String topicId, String commentId) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.can(role, Actions.Comment.DELETE);
        commentService.delete(commentId, topicId, projectId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<CommentGET> getCommentById(String version, String projectId, String topicId, String commentId) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        CommentEntity comment = commentService.getById(commentId, topicId, projectId);
        CommentGET dto = commentMapper.toDto(comment, topicId);
        dto.setAuthorization(authorizationAssembler.commentAuthorization(role));
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<CommentGET>> getTopicComment(String version, String projectId, String topicId, String $filter, String $orderby) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        List<CommentGET> comments = commentService.getAll(projectId, topicId, $filter, $orderby)
                .stream()
                .map(comment -> {
                    CommentGET dto = commentMapper.toDto(comment, topicId);
                    dto.setAuthorization(authorizationAssembler.commentAuthorization(role));
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<CommentGET> updateComment(String version, String projectId, String topicId, String commentId, CommentPUT commentPUT) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.can(role, Actions.Comment.UPDATE);
        CommentEntity updated = commentService.update(commentId, topicId, projectId, commentPUT);
        CommentGET dto = commentMapper.toDto(updated, topicId);
        dto.setAuthorization(authorizationAssembler.commentAuthorization(role));
        return ResponseEntity.ok(dto);
    }
}
