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

    @Query("""
            SELECT p.guid FROM ProjectEntity p
            JOIN p.extensions e
            JOIN e.users u
            WHERE u = :email
            """)
    List<String> findProjectGuidsByUserEmail(String email);

    @Query("""
            SELECT p.guid FROM ProjectEntity p
            JOIN p.extensions e
            WHERE NOT EXISTS (
                SELECT u FROM ExtensionEntity e2
                JOIN e2.users u
                WHERE e2.id = e.id
            )
            """)
    List<String> findPublicProjectGuids();
}
