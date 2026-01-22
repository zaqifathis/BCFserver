package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.generated.api.RelatedTopicsApi;
import de.openfabtwin.generated.dto.RelatedTopicGET;
import de.openfabtwin.generated.dto.RelatedTopicPUT;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.services.TopicService;
import de.openfabtwin.utils.BcfProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RelatedTopicController implements RelatedTopicsApi {

    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final BcfProperties props;

    @Override
    public ResponseEntity<List<RelatedTopicGET>> getRelatedTopics(String version, String projectId, String topicId) {
        props.validateVersion(version);
        List<RelatedTopicGET> relatedTopics = topicService.getRelatedTopicGuids(topicId, projectId)
                .stream()
                .map(topicMapper::toRelatedTopicDto)
                .toList();
        return ResponseEntity.ok(relatedTopics);
    }

    @Override
    public ResponseEntity<List<RelatedTopicGET>> updateRelatedTopics(String version, String projectId, String topicId, List<@Valid RelatedTopicPUT> relatedTopicPUT) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.can(role, Actions.Topic.UPDATE_RELATED_TOPICS);
        List<RelatedTopicGET> updated = topicService.updateRelatedTopics(topicId, projectId, relatedTopicPUT)
                .stream()
                .map(topicMapper::toRelatedTopicDto)
                .toList();
        return ResponseEntity.ok(updated);
    }
}
