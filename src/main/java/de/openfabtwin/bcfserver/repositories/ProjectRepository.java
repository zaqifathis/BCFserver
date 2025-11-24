package de.openfabtwin.bcfserver.repositories;

import de.openfabtwin.bcfserver.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByGuid(String guid);

    boolean existsByGuid(String guid);
}
