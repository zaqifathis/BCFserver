package de.openfabtwin.repositories;

import de.openfabtwin.entities.CommentEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentEventRepository extends JpaRepository<CommentEventEntity, Long>, JpaSpecificationExecutor<CommentEventEntity> {
}
