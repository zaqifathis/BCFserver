package de.openfabtwin.repositories;

import de.openfabtwin.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByGuid(String guid);

    boolean existsByGuid(String guid);

    List<ProjectEntity> findAllByGuidIn(List<String> guids);

    List<String> findAllGuids();

    void deleteAllByGuidIn(List<String> guids);
}
