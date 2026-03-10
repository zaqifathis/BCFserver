package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "extension_topic_types", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "topic_type")
    private List<String> topicTypes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_topic_statuses", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "topic_status")
    private List<String> topicStatuses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_priorities", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "priority")
    private List<String> priorities = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_topic_labels", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "topic_label")
    private List<String> topicLabels = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_stages", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "stage")
    private List<String> stages = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_snippet_types", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "snippet_type")
    private List<String> snippetTypes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "extension_users", joinColumns = @JoinColumn(name = "extension_id"))
    @Column(name = "user")
    private List<String> users = new ArrayList<>();

}
