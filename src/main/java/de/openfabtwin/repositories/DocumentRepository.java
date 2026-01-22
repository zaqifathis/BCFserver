package de.openfabtwin.repositories;

import de.openfabtwin.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    boolean existsByGuidAndProject_Guid(String guid, String projectGuid);

    Optional<DocumentEntity> findByGuidAndProject_Guid(String guid, String projectGuid);

}
