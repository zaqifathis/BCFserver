package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.generated.api.SnippetsApi;
import de.openfabtwin.services.*;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SnippetController implements SnippetsApi {

    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final BcfProperties props;
    private final SnippetService snippetService;

    @Override
    public ResponseEntity<Resource> getTopicSnippet(String version, String projectId, String topicId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        byte[] data = snippetService.getSnippetData(projectId, topicId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(data));
    }

    @Override
    public ResponseEntity<Void> updateTopicSnippet(String version, String projectId, String topicId, Resource body) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.UPDATE_BIM_SNIPPET);
        snippetService.updateSnippetData(projectId, topicId, body);
        return ResponseEntity.ok().build();
    }
}
