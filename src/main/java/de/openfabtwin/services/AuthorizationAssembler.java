package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.generated.dto.CommentGETAuthorization;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGETAuthorization;
import de.openfabtwin.generated.dto.TopicGETAuthorization;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationAssembler {

    private final AuthorizationService authorizationService;

    public AuthorizationAssembler(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    public ExtensionsGET applyAuthorization(ExtensionsGET dto, UserRole role) {
        dto.setProjectActions(authorizationService.getExtensionProjectActions(role));
        dto.setTopicActions(authorizationService.getExtensionTopicActions(role));
        dto.setCommentActions(authorizationService.getExtensionCommentActions(role));
        return dto;
    }

    public ProjectGETAuthorization projectAuthorization(UserRole role) {
        ProjectGETAuthorization auth = new ProjectGETAuthorization();
        auth.setProjectActions(authorizationService.getAuthorizedProjectActions(role));
        return auth;
    }

    public TopicGETAuthorization topicAuthorization(UserRole role) {
        TopicGETAuthorization auth = new TopicGETAuthorization();
        auth.setTopicActions(authorizationService.getAuthorizedTopicActions(role));
        return auth;
    }

    public CommentGETAuthorization commentAuthorization(UserRole role) {
        CommentGETAuthorization auth = new CommentGETAuthorization();
        auth.setCommentActions(authorizationService.getAuthorizedCommentActions(role));
        return auth;
    }
}
