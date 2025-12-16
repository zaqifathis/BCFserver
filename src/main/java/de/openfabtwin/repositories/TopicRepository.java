package de.openfabtwin.repositories;

import de.openfabtwin.entities.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    Optional<TopicEntity> findByGuidAndProject_Guid(String topicGuid, String projectGuid);

    Optional<TopicEntity> findByGuid(String topicId);

    Page<TopicEntity> findAllByProject_Guid(String projectId, Pageable pageable);
}
