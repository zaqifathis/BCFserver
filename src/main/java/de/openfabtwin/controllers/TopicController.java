package de.openfabtwin.controllers;

import de.openfabtwin.utils.BcfProperties;
import de.openfabtwin.api.generated.TopicsApi;
import de.openfabtwin.dto.generated.TopicGET;
import de.openfabtwin.dto.generated.TopicPOST;
import de.openfabtwin.dto.generated.TopicPUT;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopicController implements TopicsApi {

    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final BcfProperties props;

    @Override
    public ResponseEntity<TopicGET> createTopic(String version, String projectId, TopicPOST topicPOST) {
        props.validateVersion(version);
        TopicEntity created = topicService.create(projectId, topicPOST);
        return ResponseEntity.status(201).body(null);
    }

    @Override
    public ResponseEntity<Void> deleteTopic(String version, String projectId, String topicId) {
        props.validateVersion(version);
        return null;
    }

    @Override
    public ResponseEntity<TopicGET> getTopicById(String version, String projectId, String topicId) {
        props.validateVersion(version);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<TopicGET>> getTopics(String version, String projectId, String $filter, String $orderby, String $top, String $skip) {
        props.validateVersion(version);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<TopicGET> updateTopic(String version, String projectId, String topicId, TopicPUT topicPUT) {
        props.validateVersion(version);
        return ResponseEntity.ok(null);
    }
}
