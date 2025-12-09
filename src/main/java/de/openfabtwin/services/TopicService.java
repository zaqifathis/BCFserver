package de.openfabtwin.services;

import de.openfabtwin.dto.generated.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        var topic = new TopicEntity();

        return topicRepository.save(topic);
    }
}
