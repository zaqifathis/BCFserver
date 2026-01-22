package de.openfabtwin.entities;

import de.openfabtwin.services.ViewpointService.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viewpoints")
@Data
public class ViewpointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @Column(name = "viewpoint_index")
    private Integer index;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String payload;

    @Column(columnDefinition = "VARBINARY")
    private byte[] snapshotData;

    @Enumerated(EnumType.STRING)
    private ImageType snapshotType;

    @OneToMany(mappedBy = "viewpoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BitmapEntity> bitmaps = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
}


