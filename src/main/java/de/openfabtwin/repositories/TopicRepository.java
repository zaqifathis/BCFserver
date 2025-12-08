package de.openfabtwin.repositories;

import de.openfabtwin.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

}
