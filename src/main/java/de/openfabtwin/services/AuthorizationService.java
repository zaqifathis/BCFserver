package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.auth.Actions.*;
import de.openfabtwin.generated.dto.CommentGETAuthorization;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGETAuthorization;
import de.openfabtwin.generated.dto.TopicGETAuthorization;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorizationService {

    public void assertCan(UserRole role, Project action) {
        if (!can(role, action)) {
            throw new AccessDeniedException("User role " + role + " cannot perform action " + action);
        }
    }

    public void assertCan(UserRole role, Topic action) {
        if (!can(role, action)) {
            throw new AccessDeniedException("User role " + role + " cannot perform action " + action);
        }
    }

    public void assertCan(UserRole role, Comment action) {
        if (!can(role, action)) {
            throw new AccessDeniedException("User role " + role + " cannot perform action " + action);
        }
    }

    public boolean can(UserRole role, Project action) {
        if (role == UserRole.ADMIN) return true;
        return action != Project.UPDATE;
    }

    public boolean can(UserRole role, Topic action) {
        if (role == UserRole.ADMIN) return true;
        return action == Topic.CREATE_COMMENT ||
               action == Topic.CREATE_VIEWPOINT;
    }

    public boolean can(UserRole role, Comment action) {
        if (role == UserRole.ADMIN) return true;
        return action == Comment.UPDATE;
    }

    public List<ProjectGETAuthorization.ProjectActionsEnum> getAuthorizedProjectActions(UserRole role) {
        return Arrays.stream(ProjectGETAuthorization.ProjectActionsEnum.values())
                .filter(action -> can(role, Project.valueOf(action.name())))
                .toList();
    }

    public List<TopicGETAuthorization.TopicActionsEnum> getAuthorizedTopicActions(UserRole role) {
        return Arrays.stream(TopicGETAuthorization.TopicActionsEnum.values())
                .filter(action -> can(role, Topic.valueOf(action.name())))
                .toList();
    }

    public List<CommentGETAuthorization.CommentActionsEnum> getAuthorizedCommentActions(UserRole role) {
        return Arrays.stream(CommentGETAuthorization.CommentActionsEnum.values())
                .filter(action -> can(role, Comment.valueOf(action.name())))
                .toList();
    }

    public List<ExtensionsGET.ProjectActionsEnum> getExtensionProjectActions(UserRole role) {
        return Arrays.stream(ExtensionsGET.ProjectActionsEnum.values())
                .filter(action -> can(role, Project.valueOf(action.name())))
                .toList();
    }

    public List<ExtensionsGET.TopicActionsEnum> getExtensionTopicActions(UserRole role) {
        return Arrays.stream(ExtensionsGET.TopicActionsEnum.values())
                .filter(action -> can(role, Topic.valueOf(action.name())))
                .toList();
    }

    public List<ExtensionsGET.CommentActionsEnum> getExtensionCommentActions(UserRole role) {
        return Arrays.stream(ExtensionsGET.CommentActionsEnum.values())
                .filter(action -> can(role, Comment.valueOf(action.name())))
                .toList();
    }
}
