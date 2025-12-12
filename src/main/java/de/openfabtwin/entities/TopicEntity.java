package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"guid", "project_id"})
        })
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "server_assigned_id")
    private String serverAssignedId;

    @Column(name = "topic_type")
    private String topicType;

    @Column(name = "topic_status")
    private String topicStatus;

    @Column(nullable = false)
    private String title;

    private String priority;

    @Column(name = "index")
    private Integer index;

    @Column(nullable = false)
    private Instant creationDate;

    @Column(nullable = false)
    private String creationAuthor;

    private Instant modifiedDate;
    private String modifiedAuthor;

    private Instant dueDate;
    private String assignedTo;

    private String stage;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "topic_labels", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "label")
    private List<String> labels = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "topic_reference_links", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "reference_link")
    private List<String> referenceLinks = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "topic_related_topics", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "related_topic_guid")
    private List<String> relatedTopics = new ArrayList<>();

    // Complex types:
    @OneToOne(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private BimSnippetEntity bimSnippet;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentReferenceEntity> documentReferences = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViewPointEntity> viewpoints = new ArrayList<>();
}
