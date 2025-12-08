package de.openfabtwin.controllers;

import de.openfabtwin.BcfProperties;
import de.openfabtwin.api.generated.TopicsApi;
import de.openfabtwin.dto.generated.TopicGET;
import de.openfabtwin.dto.generated.TopicPOST;
import de.openfabtwin.dto.generated.TopicPUT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopicController implements TopicsApi {

    private final BcfProperties props;

    @Override
    public ResponseEntity<TopicGET> createTopic(String version, String projectId, TopicPOST topicPOST) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTopic(String version, String projectId, String topicId) {
        return null;
    }

    @Override
    public ResponseEntity<TopicGET> getTopicById(String version, String projectId, String topicId) {
        return null;
    }

    @Override
    public ResponseEntity<List<TopicGET>> getTopics(String version, String projectId, String $filter, String $orderby, String $top, String $skip) {
        return null;
    }

    @Override
    public ResponseEntity<TopicGET> updateTopic(String version, String projectId, String topicId, TopicPUT topicPUT) {
        return null;
    }
}
