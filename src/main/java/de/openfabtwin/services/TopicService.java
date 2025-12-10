package de.openfabtwin.services;

import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        if(topicPOST.getTitle() == null || topicPOST.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        // check project action if include createTopic


        var topic = new TopicEntity();
        topic.setGuid(UUID.randomUUID().toString());


        return topicRepository.save(topic);
    }
}
