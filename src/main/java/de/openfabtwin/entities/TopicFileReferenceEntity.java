package de.openfabtwin.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "topic_file_references")
@Data
public class TopicFileReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private TopicEntity topic;

    @ManyToOne(optional = false)
    private FileEntity file;

    private String ifcProjectGuid;

    private String ifcSpatialStructureElementGuid;

    private boolean external = true;


}
