package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.services.AuthorizationAssembler;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.utils.BcfProperties;
import de.openfabtwin.generated.api.TopicsApi;
import de.openfabtwin.generated.dto.TopicGET;
import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.generated.dto.TopicPUT;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopicController implements TopicsApi {

    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final AuthorizationAssembler authorizationAssembler;
    private final BcfProperties props;

    @Override
    public ResponseEntity<TopicGET> createTopic(String version, String projectId, TopicPOST topicPOST) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Project.CREATE_TOPIC);
        TopicEntity created = topicService.create(projectId, topicPOST);
        TopicGET dto = topicMapper.toDto(created);
        dto.setAuthorization(authorizationAssembler.topicAuthorization(role));
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteTopic(String version, String projectId, String topicId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.DELETE);
        topicService.delete(topicId, projectId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<TopicGET> getTopicById(String version, String projectId, String topicId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        TopicEntity topic = topicService.getById(topicId, projectId);
        TopicGET dto = topicMapper.toDto(topic);
        dto.setAuthorization(authorizationAssembler.topicAuthorization(role));
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<TopicGET>> getTopics(String version, String projectId, String $filter, String $orderby, String $top, String $skip) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        List<TopicGET> topics = topicService.getAll(projectId, $filter, $orderby, $top, $skip)
                .stream()
                .map(topic -> {
                    TopicGET dto = topicMapper.toDto(topic);
                    dto.setAuthorization(authorizationAssembler.topicAuthorization(role));
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(topics);
    }

    @Override
    public ResponseEntity<TopicGET> updateTopic(String version, String projectId, String topicId, TopicPUT topicPUT) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.UPDATE);
        TopicEntity updated = topicService.update(topicId, projectId, topicPUT);
        TopicGET dto = topicMapper.toDto(updated);
        dto.setAuthorization(authorizationAssembler.topicAuthorization(role));
        return ResponseEntity.ok(dto);
    }
}
