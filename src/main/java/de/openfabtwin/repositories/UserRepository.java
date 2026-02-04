package de.openfabtwin.repositories;

import de.openfabtwin.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}

