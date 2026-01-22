package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bim_snippets")
@Data
public class BimSnippetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String snippetType;

    private String reference;
    private String referenceSchema;

    @Column(nullable = false)
    private Boolean isExternal;

    @OneToMany(mappedBy = "bimSnippet", fetch = FetchType.LAZY)
    private Set<TopicEntity> topics = new HashSet<>();
}