package de.openfabtwin.repositories;

import de.openfabtwin.entities.BitmapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BitmapRepository extends JpaRepository<BitmapEntity, Long> {

    Optional<BitmapEntity> findByGuidAndViewpoint_Guid(String bitmapGuid, String viewpointGuid);
}
