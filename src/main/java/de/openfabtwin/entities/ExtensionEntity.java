package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_extensions")
@Data
public class ExtensionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, unique = true)
    private ProjectEntity project;

}
