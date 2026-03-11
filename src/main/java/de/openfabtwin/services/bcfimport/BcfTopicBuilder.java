package de.openfabtwin.services.bcfimport;

import de.openfabtwin.entities.*;
import de.openfabtwin.generated.markup.*;
import de.openfabtwin.generated.visinfo.VisualizationInfo;
import de.openfabtwin.services.ViewpointService.ImageType;
import de.openfabtwin.utils.BcfZipReader.TopicFolder;
import de.openfabtwin.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BcfTopicBuilder {

    private final BcfCommentBuilder commentBuilder;
    private final BcfViewpointBuilder viewpointBuilder;

    public TopicEntity build(TopicFolder folder, ProjectEntity project) {
        Topic t = folder.markup().getTopic();

        TopicEntity entity = new TopicEntity();
        entity.setProject(project);
        entity.setGuid(folder.guid());
        entity.setTitle(t.getTitle());
        entity.setTopicType(t.getTopicType());
        entity.setTopicStatus(t.getTopicStatus());
        entity.setPriority(t.getPriority());
        entity.setIndex(t.getIndex());
        entity.setDescription(t.getDescription());
        entity.setAssignedTo(t.getAssignedTo());
        entity.setStage(t.getStage());
        entity.setServerAssignedId(t.getServerAssignedId());
        entity.setCreationDate(DateUtils.toInstant(t.getCreationDate()));
        entity.setCreationAuthor(t.getCreationAuthor());
        entity.setModifiedDate(DateUtils.toInstant(t.getModifiedDate()));
        entity.setModifiedAuthor(t.getModifiedAuthor());
        entity.setDueDate(DateUtils.toInstant(t.getDueDate()));

        if (t.getLabels() != null && t.getLabels().getLabel() != null)
            entity.setLabels(new ArrayList<>(t.getLabels().getLabel()));

        if (t.getReferenceLinks() != null && t.getReferenceLinks().getReferenceLink() != null)
            entity.setReferenceLinks(new ArrayList<>(t.getReferenceLinks().getReferenceLink()));

        if (t.getRelatedTopics() != null && t.getRelatedTopics().getRelatedTopic() != null)
            entity.setRelatedTopics(t.getRelatedTopics().getRelatedTopic().stream()
                    .map(Topic.RelatedTopics.RelatedTopic::getGuid).toList());

        // Files - Header
        Header header = folder.markup().getHeader();
        if (header != null && header.getFiles() != null && header.getFiles().getFile() != null) {
            for (File f : header.getFiles().getFile()) {
                FileEntity fileEntity = findOrCreateFile(f, project);
                TopicFileReferenceEntity ref = new TopicFileReferenceEntity();
                ref.setTopic(entity);
                ref.setFile(fileEntity);
                ref.setIfcProjectGuid(f.getIfcProject());
                ref.setIfcSpatialStructureElementGuid(f.getIfcSpatialStructureElement());
                ref.setExternal(f.isIsExternal());
                entity.getFileReferences().add(ref);
            }
        }

        if (t.getBimSnippet() != null)
            entity.setBimSnippet(buildBimSnippet(t.getBimSnippet()));

        if (t.getDocumentReferences() != null && t.getDocumentReferences().getDocumentReference() != null) {
            for (DocumentReference dr : t.getDocumentReferences().getDocumentReference())
                entity.getDocumentReferences().add(buildDocumentReference(dr, entity, project));
        }

        // Viewpoints — tracked locally so comments can link to them
        List<ViewpointEntity> builtViewpoints = new ArrayList<>();
        if (t.getViewpoints() != null && t.getViewpoints().getViewPoint() != null) {
            for (ViewPoint vp : t.getViewpoints().getViewPoint()) {
                VisualizationInfo vi = folder.viewpoints().get(vp.getViewpoint());
                if (vi == null) continue;
                byte[] snapBytes = vp.getSnapshot() != null ? folder.snapshots().get(vp.getSnapshot()) : null;
                ImageType snapType = BcfViewpointBuilder.resolveImageType(vp.getSnapshot());
                int index = vp.getIndex() != null ? vp.getIndex() : builtViewpoints.size();
                ViewpointEntity viewpointEntity = viewpointBuilder.build(vi, snapBytes, snapType, index, entity, folder);
                entity.getViewpoints().add(viewpointEntity);
                builtViewpoints.add(viewpointEntity);
            }
        }

        if (t.getComments() != null && t.getComments().getComment() != null) {
            for (Comment c : t.getComments().getComment())
                entity.getComments().add(commentBuilder.build(c, entity, builtViewpoints));
        }

        return entity;
    }

    public void merge(TopicFolder folder, TopicEntity topic, ProjectEntity project) {
        Topic t = folder.markup().getTopic();

        // Scalar fields — always overwrite from BCF file
        topic.setTopicType(t.getTopicType());
        topic.setTopicStatus(t.getTopicStatus());
        topic.setPriority(t.getPriority());
        topic.setAssignedTo(t.getAssignedTo());
        topic.setStage(t.getStage());
        topic.setDueDate(DateUtils.toInstant(t.getDueDate()));
        topic.setModifiedDate(DateUtils.toInstant(t.getModifiedDate()));
        topic.setModifiedAuthor(t.getModifiedAuthor());

        // Collection fields — replace entirely
        topic.setLabels(t.getLabels() != null && t.getLabels().getLabel() != null
                ? new ArrayList<>(t.getLabels().getLabel()) : new ArrayList<>());

        topic.setRelatedTopics(t.getRelatedTopics() != null && t.getRelatedTopics().getRelatedTopic() != null
                ? t.getRelatedTopics().getRelatedTopic().stream()
                .map(Topic.RelatedTopics.RelatedTopic::getGuid).toList()
                : new ArrayList<>());

        // BimSnippet — replace reference
        topic.setBimSnippet(t.getBimSnippet() != null ? buildBimSnippet(t.getBimSnippet()) : null);

        // DocumentReferences — add only new guids
        if (t.getDocumentReferences() != null && t.getDocumentReferences().getDocumentReference() != null) {
            for (DocumentReference dr : t.getDocumentReferences().getDocumentReference()) {
                boolean exists = topic.getDocumentReferences().stream()
                        .anyMatch(e -> e.getGuid() != null && e.getGuid().equals(dr.getGuid()));
                if (!exists)
                    topic.getDocumentReferences().add(buildDocumentReference(dr, topic, project));
            }
        }

        // Viewpoints — add only new guids; keep combined list for comment linking
        List<ViewpointEntity> allViewpoints = new ArrayList<>(topic.getViewpoints());
        if (t.getViewpoints() != null && t.getViewpoints().getViewPoint() != null) {
            for (ViewPoint vp : t.getViewpoints().getViewPoint()) {
                boolean exists = topic.getViewpoints().stream()
                        .anyMatch(e -> e.getGuid().equals(vp.getGuid()));
                if (exists) continue;

                VisualizationInfo vi = folder.viewpoints().get(vp.getViewpoint());
                if (vi == null) continue;

                byte[] snapBytes = vp.getSnapshot() != null ? folder.snapshots().get(vp.getSnapshot()) : null;
                ImageType snapType = BcfViewpointBuilder.resolveImageType(vp.getSnapshot());
                int index = vp.getIndex() != null ? vp.getIndex() : allViewpoints.size();
                ViewpointEntity newVp = viewpointBuilder.build(vi, snapBytes, snapType, index, topic, folder);
                topic.getViewpoints().add(newVp);
                allViewpoints.add(newVp);
            }
        }

        // Comments — add only new guids
        if (t.getComments() != null && t.getComments().getComment() != null) {
            for (Comment c : t.getComments().getComment()) {
                boolean exists = topic.getComments().stream()
                        .anyMatch(e -> e.getGuid().equals(c.getGuid()));
                if (!exists)
                    topic.getComments().add(commentBuilder.build(c, topic, allViewpoints));
            }
        }
    }

    // -----------------

    private BimSnippetEntity buildBimSnippet(BimSnippet snippet) {
        BimSnippetEntity entity = new BimSnippetEntity();
        entity.setSnippetType(snippet.getSnippetType());
        entity.setReference(snippet.getReference());
        entity.setReferenceSchema(snippet.getReferenceSchema());
        entity.setIsExternal(snippet.isIsExternal());
        return entity;
    }

    private DocumentReferenceEntity buildDocumentReference(DocumentReference dr, TopicEntity topic, ProjectEntity project) {
        DocumentReferenceEntity entity = new DocumentReferenceEntity();
        entity.setTopic(topic);
        entity.setGuid(dr.getGuid());
        entity.setDescription(dr.getDescription());
        if (dr.getDocumentGuid() != null) {
            project.getDocuments().stream()
                    .filter(d -> dr.getDocumentGuid().equals(d.getGuid()))
                    .findFirst()
                    .ifPresent(entity::setDocument);
        } else {
            entity.setUrl(dr.getUrl());
        }
        return entity;
    }

    private FileEntity findOrCreateFile(File f, ProjectEntity project) {
        String key = f.getFilename() != null ? f.getFilename() : f.getReference();

        FileEntity existing = project.getFiles().stream()
                .filter(fe -> key != null && key.equals(
                        fe.getFilename() != null ? fe.getFilename() : fe.getReference()))
                .findFirst()
                .orElse(null);

        if (existing != null) return existing;

        FileEntity fileEntity = new FileEntity();
        fileEntity.setProject(project);
        fileEntity.setFilename(f.getFilename());
        fileEntity.setReference(f.getReference());
        fileEntity.setDate(f.getDate() != null ? DateUtils.toInstant(f.getDate()).toString() : Instant.now().toString());
        project.getFiles().add(fileEntity);
        return fileEntity;
    }
}