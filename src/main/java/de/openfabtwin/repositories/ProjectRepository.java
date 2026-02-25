package de.openfabtwin.repositories;

import de.openfabtwin.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByGuid(String guid);

    boolean existsByGuid(String guid);

    List<ProjectEntity> findAllByGuidIn(List<String> guids);

    @Query("SELECT p.guid FROM ProjectEntity p")
    List<String> findAllGuids();

    @Modifying
    @Transactional
    @Query("DELETE FROM ProjectEntity p WHERE p.guid IN :guids")
    void deleteByGuidIn(List<String> guids);
}
