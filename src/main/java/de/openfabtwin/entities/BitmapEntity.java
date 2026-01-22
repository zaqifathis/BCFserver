package de.openfabtwin.entities;

import de.openfabtwin.services.ViewpointService.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "viewpoint_bitmaps")
@Data
public class BitmapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;

    @Enumerated(EnumType.STRING)
    private ImageType bitmapType;

    @Column(columnDefinition = "VARBINARY")
    private byte[] bitmapData;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String bitmapMetadata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewpoint_id", nullable = false)
    private ViewpointEntity viewpoint;
}
