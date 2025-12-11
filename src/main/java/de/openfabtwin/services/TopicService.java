package de.openfabtwin.services;

import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ExtensionRepository extensionRepository;

    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        // check project action if includes createTopic

        // check user permissions


        if(topicPOST.getTitle() == null || topicPOST.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");

        }

        var topic = new TopicEntity();
        topic.setGuid(UUID.randomUUID().toString());


        return topicRepository.save(topic);
    }
}
