package de.openfabtwin.entities;

import de.openfabtwin.auth.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(unique = true)
    private String email;

    private String name;

    @ManyToMany(mappedBy = "members")
    private Set<ProjectEntity> projects = new HashSet<>();
}
