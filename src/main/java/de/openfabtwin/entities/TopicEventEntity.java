package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "topic_events")
@Data
public class TopicEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_guid", nullable = false)
    private String projectGuid;

    @Column(name = "topic_guid", nullable = false)
    private String topicGuid;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private TopicEventType eventType;

    @Column(name = "event_value")
    private String eventValue;

    @Column(name = "event_date", nullable = false)
    private Instant eventDate;
}
