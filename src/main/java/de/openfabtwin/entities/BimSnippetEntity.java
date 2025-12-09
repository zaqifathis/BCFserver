package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bim_snippets")
@Data
public class BimSnippetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Required in BCF
    @Column(nullable = false)
    private String snippetType;

    private String reference;
    private String referenceSchema;

    @Column(nullable = false)
    private Boolean isExternal;

    @OneToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
}