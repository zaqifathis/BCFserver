package de.openfabtwin.repositories;

import de.openfabtwin.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByProject_Guid(String projectId);

    Optional<FileEntity> findByProject_GuidAndReference(String projectId, String reference);
}
