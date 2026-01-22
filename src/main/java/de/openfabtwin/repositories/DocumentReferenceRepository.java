package de.openfabtwin.repositories;

import de.openfabtwin.entities.DocumentReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentReferenceRepository extends JpaRepository<DocumentReferenceEntity, Long> {

    Optional<DocumentReferenceEntity> findByGuidAndTopic_Guid(String docRefId, String topicId);
}
