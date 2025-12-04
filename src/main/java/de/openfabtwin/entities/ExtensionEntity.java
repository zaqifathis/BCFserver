package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "extensions")
@Data
public class ExtensionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @ElementCollection
    @CollectionTable(name = "ext_topic_type", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> topicType;

    @ElementCollection
    @CollectionTable(name = "ext_topic_status", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> topicStatus;

    @ElementCollection
    @CollectionTable(name = "ext_topic_label", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> topicLabel;

    @ElementCollection
    @CollectionTable(name = "ext_snippet_type", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> snippetType;

    @ElementCollection
    @CollectionTable(name = "ext_priority", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> priority;

    @ElementCollection
    @CollectionTable(name = "ext_users", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> users;

    @ElementCollection
    @CollectionTable(name = "ext_stage", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "item")
    private List<String> stage;
}
