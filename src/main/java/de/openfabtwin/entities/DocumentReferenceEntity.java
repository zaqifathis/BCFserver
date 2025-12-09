package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "document_references")
@Data
public class DocumentReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    private String documentGuid;

    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
}