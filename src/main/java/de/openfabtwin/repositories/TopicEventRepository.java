package de.openfabtwin.repositories;

import de.openfabtwin.entities.TopicEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TopicEventRepository extends JpaRepository<TopicEventEntity, Long>, JpaSpecificationExecutor<TopicEventEntity> {
}
