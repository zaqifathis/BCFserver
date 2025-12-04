package de.openfabtwin.repositories;

import de.openfabtwin.entities.ExtensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<ExtensionEntity, Long> {

    Optional<ExtensionEntity> findByProject_Guid(String guid);

    Optional<ExtensionEntity> findByProjectId(Long projectId);
}
