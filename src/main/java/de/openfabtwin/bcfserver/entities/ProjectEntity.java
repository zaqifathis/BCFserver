package de.openfabtwin.bcfserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String createdAt;
}
