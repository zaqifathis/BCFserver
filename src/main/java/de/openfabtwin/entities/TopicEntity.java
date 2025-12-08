package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @Column(name = "server_assigned_id")
    private String serverAssignedId;

    @Column(name = "topic_type")
    private String topicType;

    @Column(name = "topic_status")
    private String topicStatus;

    @Column(nullable = false)
    private String title;

    private String priority;
    private Integer indexNumber;
    private Instant creationDate;

    @Column(nullable = false)
    private String creationAuthor;

    private Instant modifiedDate;
    private String modifiedAuthor;

    private Instant dueDate;
    private String assignedTo;

    @Column(nullable = false)
    private List<String> stage;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "topic_labels", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "label")
    private List<String> labels;

}
