package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "viewpoints")
@Data
public class ViewPointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    private String viewpointFile;

    private String snapshotFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
}
