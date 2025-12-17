package de.openfabtwin.repositories;

import de.openfabtwin.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long>, JpaSpecificationExecutor<TopicEntity> {

    Optional<TopicEntity> findByGuidAndProject_Guid(String topicGuid, String projectGuid);

}
