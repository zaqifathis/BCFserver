package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ExtensionEntity extensions;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TopicEntity> topics = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> files = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DocumentEntity> documents = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> members = new HashSet<>();
}
