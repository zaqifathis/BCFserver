package de.openfabtwin.repositories;

import de.openfabtwin.entities.TopicFileReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicFileReferenceRepository extends JpaRepository<TopicFileReferenceEntity, Long> {
    List<TopicFileReferenceEntity> findByTopic_Project_GuidAndTopic_Guid(String projectId, String topicId);
}
