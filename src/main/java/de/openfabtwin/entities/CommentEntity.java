package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "comments")
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @Column(name= "creation_date", nullable = false)
    private Instant date;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private String viewpointGuid;

    private Instant modifiedDate;
    private String modifiedAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
}