package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    private String filename;

    private String reference;

    private String date;

    @OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
    private List<TopicFileReferenceEntity> topicReferences = new ArrayList<>();

}
