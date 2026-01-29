package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "comment_events")
@Data
public class CommentEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_guid", nullable = false)
    private String projectGuid;

    @Column(name = "topic_guid", nullable = false)
    private String topicGuid;

    @Column(name = "comment_guid", nullable = false)
    private String commentGuid;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private CommentEventType eventType;

    @Column(name = "event_value")
    private String eventValue;

    @Column(name = "event_date", nullable = false)
    private Instant eventDate;

}
