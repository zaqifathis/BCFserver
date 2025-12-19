package de.openfabtwin.repositories;

import de.openfabtwin.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>, JpaSpecificationExecutor<CommentEntity> {

    Optional<CommentEntity> findByGuidAndTopic_GuidAndTopic_Project_Guid(String commentId, String topicId, String projectId);
}
