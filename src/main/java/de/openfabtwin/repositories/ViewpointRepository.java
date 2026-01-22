package de.openfabtwin.repositories;

import de.openfabtwin.entities.ViewpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ViewpointRepository extends JpaRepository<ViewpointEntity, Long> {

    Optional<ViewpointEntity> findByGuidAndTopic_Guid(String viewpointGuid, String topicGuid);

    boolean existsByGuid(String guid);

    Collection<ViewpointEntity> findAllByTopic_Guid(String topicId);
}
