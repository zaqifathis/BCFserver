package de.openfabtwin.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.openfabtwin.entities.*;
import de.openfabtwin.generated.markup.*;
import de.openfabtwin.generated.visinfo.*;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.utils.BcfZipReader.*;
import de.openfabtwin.utils.BcfZipReader;
import de.openfabtwin.generated.extensions.Extensions;
import de.openfabtwin.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.openfabtwin.services.ViewpointService.ImageType;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class BcfXmlImportService {

    private final ProjectRepository projectRepository;
    private final SecurityContextService securityContextService;
    private final ObjectMapper objectMapper;

    public void validateFileExtension(String filename) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IOException("Filename is missing");
        }
        String lower = filename.toLowerCase();
        if (!lower.endsWith(".bcf")) {
            throw new IOException("Invalid file type: '" + filename + "'. Only .bcf file is supported.");
        }
    }

    @Transactional
    public void importBcf(byte[] zipBytes) throws IOException {
        int projectCount = (int) projectRepository.count();
        BcfParseResult result = BcfZipReader.read(zipBytes, projectCount);

        String projectGuid = result.root().projectId();
        ProjectEntity existing = projectRepository.findByGuid(projectGuid).orElse(null);

        ProjectEntity project = existing != null
                ? mergeProject(existing, result)
                : buildProject(result);
        projectRepository.save(project);
    }

    //----------------- PROJECT -----------------+

    private ProjectEntity buildProject(BcfParseResult result) {
        var root = result.root();

        ProjectEntity project = new ProjectEntity();
        project.setGuid(root.projectId());
        project.setName(root.projectName());

        ExtensionEntity ext = root.extensions() != null ?
                buildExtensions(root.extensions(), project) : buildDefaultExtensions(project);
        project.setExtensions(ext);

        if (root.documentInfo() != null && root.documentInfo().getDocuments() != null) {
            for (var doc : root.documentInfo().getDocuments().getDocument()) {
                byte[] data = result.documents().get(doc.getGuid());
                DocumentEntity docEntity = new DocumentEntity();
                docEntity.setProject(project);
                docEntity.setGuid(doc.getGuid());
                docEntity.setFilename(doc.getFilename());
                docEntity.setData(data);
                project.getDocuments().add(docEntity);
            }
        }

        for (TopicFolder folder : result.topics()) {
            TopicEntity topic = buildTopic(folder, project);
            project.getTopics().add(topic);
        }

        return project;
    }

    private ProjectEntity mergeProject(ProjectEntity existingProject, BcfParseResult result) {
        var root = result.root();

        // always update existingProject name from the new file
        existingProject.setName(root.projectName());

        // extensions: replace entirely with new file's extensions
        ExtensionEntity ext = existingProject.getExtensions();
        if (root.extensions() != null) {
            updateExtensions(ext, root.extensions());
        } else {
            clearExtensions(ext);
        }

        // documents: only add those whose guid is not stored
        if (root.documentInfo() != null && root.documentInfo().getDocuments() != null) {
            for (var doc : root.documentInfo().getDocuments().getDocument()) {
                boolean alreadyExists = existingProject.getDocuments().stream()
                        .anyMatch(d -> d.getGuid().equals(doc.getGuid()));
                if (alreadyExists) continue;

                byte[] data = result.documents().get(doc.getGuid());
                DocumentEntity docEntity = new DocumentEntity();
                docEntity.setProject(existingProject);
                docEntity.setGuid(doc.getGuid());
                docEntity.setFilename(doc.getFilename());
                docEntity.setData(data);
                existingProject.getDocuments().add(docEntity);
            }
        }

        // topics: only add those whose guid is not stored
        for (TopicFolder folder : result.topics()) {
            boolean alreadyExists = existingProject.getTopics().stream()
                    .anyMatch(t -> t.getGuid().equals(folder.guid()));
            if (alreadyExists) continue;

            TopicEntity topic = buildTopic(folder, existingProject);
            existingProject.getTopics().add(topic);
        }

        return existingProject;
    }

    //----------------- EXTENSION -----------------+

    private ExtensionEntity buildExtensions(Extensions extensions, ProjectEntity project) {
        ExtensionEntity entity = new ExtensionEntity();
        entity.setProject(project);

        if (extensions.getTopicTypes() != null && extensions.getTopicTypes().getTopicType() != null)
            entity.setTopicTypes(new ArrayList<>(extensions.getTopicTypes().getTopicType()));

        if (extensions.getTopicStatuses() != null && extensions.getTopicStatuses().getTopicStatus() != null)
            entity.setTopicStatuses(new ArrayList<>(extensions.getTopicStatuses().getTopicStatus()));

        if (extensions.getPriorities() != null && extensions.getPriorities().getPriority() != null)
            entity.setPriorities(new ArrayList<>(extensions.getPriorities().getPriority()));

        if (extensions.getTopicLabels() != null && extensions.getTopicLabels().getTopicLabel() != null)
            entity.setTopicLabels(new ArrayList<>(extensions.getTopicLabels().getTopicLabel()));

        if (extensions.getStages() != null && extensions.getStages().getStage() != null)
            entity.setStages(new ArrayList<>(extensions.getStages().getStage()));

        if (extensions.getSnippetTypes() != null && extensions.getSnippetTypes().getSnippetType() != null)
            entity.setSnippetTypes(new ArrayList<>(extensions.getSnippetTypes().getSnippetType()));

        if (extensions.getUsers() != null && extensions.getUsers().getUser() != null) {
            entity.setUsers(new ArrayList<>(extensions.getUsers().getUser()));
            String currentUser = securityContextService.getCurrentUserEmail();
            if (!extensions.getUsers().getUser().contains(currentUser)) entity.getUsers().add(currentUser);
        }
        return entity;
    }

    private ExtensionEntity buildDefaultExtensions(ProjectEntity project){
        ExtensionEntity ext = new ExtensionEntity();
        ext.setProject(project);
        return ext;
    }

    private void updateExtensions(ExtensionEntity ext, Extensions extensions) {
        ext.setTopicTypes(extensions.getTopicTypes() != null && extensions.getTopicTypes().getTopicType() != null
                ? new ArrayList<>(extensions.getTopicTypes().getTopicType()) : new ArrayList<>());
        ext.setTopicStatuses(extensions.getTopicStatuses() != null && extensions.getTopicStatuses().getTopicStatus() != null
                ? new ArrayList<>(extensions.getTopicStatuses().getTopicStatus()) : new ArrayList<>());
        ext.setPriorities(extensions.getPriorities() != null && extensions.getPriorities().getPriority() != null
                ? new ArrayList<>(extensions.getPriorities().getPriority()) : new ArrayList<>());
        ext.setTopicLabels(extensions.getTopicLabels() != null && extensions.getTopicLabels().getTopicLabel() != null
                ? new ArrayList<>(extensions.getTopicLabels().getTopicLabel()) : new ArrayList<>());
        ext.setStages(extensions.getStages() != null && extensions.getStages().getStage() != null
                ? new ArrayList<>(extensions.getStages().getStage()) : new ArrayList<>());
        ext.setSnippetTypes(extensions.getSnippetTypes() != null && extensions.getSnippetTypes().getSnippetType() != null
                ? new ArrayList<>(extensions.getSnippetTypes().getSnippetType()) : new ArrayList<>());

        List<String> users = extensions.getUsers() != null && extensions.getUsers().getUser() != null
                ? new ArrayList<>(extensions.getUsers().getUser()) : new ArrayList<>();
        String currentUser = securityContextService.getCurrentUserEmail();
        if (!users.contains(currentUser)) users.add(currentUser);
        ext.setUsers(users);
    }

    private void clearExtensions(ExtensionEntity ext) {
        ext.setTopicTypes(new ArrayList<>());
        ext.setTopicStatuses(new ArrayList<>());
        ext.setPriorities(new ArrayList<>());
        ext.setTopicLabels(new ArrayList<>());
        ext.setStages(new ArrayList<>());
        ext.setSnippetTypes(new ArrayList<>());
        String currentUser = securityContextService.getCurrentUserEmail();
        ext.setUsers(new ArrayList<>(List.of(currentUser)));
    }

    //----------------- TOPIC -----------------+

    private TopicEntity buildTopic(TopicFolder folder, ProjectEntity project) {
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

        if (t.getLabels() != null && t.getLabels().getLabel() != null) {
            entity.setLabels(new ArrayList<>(t.getLabels().getLabel()));
        }

        // Reference Links
        if (t.getReferenceLinks() != null && t.getReferenceLinks().getReferenceLink() != null) {
            entity.setReferenceLinks(new ArrayList<>(t.getReferenceLinks().getReferenceLink()));
        }

        if (t.getRelatedTopics() != null && t.getRelatedTopics().getRelatedTopic() != null) {
            List<String> related = t.getRelatedTopics().getRelatedTopic()
                    .stream()
                    .map(Topic.RelatedTopics.RelatedTopic::getGuid)
                    .toList();
            entity.setRelatedTopics(related);
        }

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
                entity.getFileReferences().add(ref);
            }
        }

        // Bim Snippets
        if (t.getBimSnippet() != null) {
            entity.setBimSnippet(buildBimSnippet(t.getBimSnippet()));
        }

        // Document References
        if (t.getDocumentReferences() != null && t.getDocumentReferences().getDocumentReference() != null) {
            for (DocumentReference dr : t.getDocumentReferences().getDocumentReference()) {
                DocumentReferenceEntity docRef = buildDocumentReference(dr, entity, project);
                entity.getDocumentReferences().add(docRef);
            }
        }

        // Viewpoints
        List<ViewpointEntity> builtViewpoints = new ArrayList<>();
        if (t.getViewpoints() != null && t.getViewpoints().getViewPoint() != null) {
            for (ViewPoint vp : t.getViewpoints().getViewPoint()) {
                VisualizationInfo vi = folder.viewpoints().get(vp.getViewpoint());
                if (vi == null) continue;
                byte[] snapBytes = vp.getSnapshot() != null ? folder.snapshots().get(vp.getSnapshot()) : null;
                ImageType snapType = resolveImageType(vp.getSnapshot());
                int index = vp.getIndex() != null ? vp.getIndex() : builtViewpoints.size();
                ViewpointEntity viewpointEntity = buildViewpoint(vi, snapBytes, snapType, index, entity, folder);
                entity.getViewpoints().add(viewpointEntity);
                builtViewpoints.add(viewpointEntity);
            }
        }

        // Comments
        if (t.getComments() != null && t.getComments().getComment() != null) {
            for (Comment c : t.getComments().getComment()) {
                CommentEntity comment = buildComment(c, entity, builtViewpoints);
                entity.getComments().add(comment);
            }
        }

        return entity;
    }

    //----------------- COMMENT -----------------+

    private CommentEntity buildComment(Comment c, TopicEntity topic, List<ViewpointEntity> viewpoints) {
        CommentEntity entity = new CommentEntity();
        entity.setTopic(topic);
        entity.setGuid(c.getGuid());
        entity.setAuthor(c.getAuthor());
        entity.setComment(c.getComment());
        entity.setDate(DateUtils.toInstant(c.getDate()));
        entity.setModifiedDate(DateUtils.toInstant(c.getModifiedDate()));
        entity.setModifiedAuthor(c.getModifiedAuthor());

        if (c.getViewpoint() != null && c.getViewpoint().getGuid() != null) {
            viewpoints.stream()
                    .filter(vp -> vp.getGuid().equals(c.getViewpoint().getGuid()))
                    .findFirst()
                    .ifPresent(entity::setViewpoint);
        }

        return entity;
    }

    //----------------- Viewpoint -----------------+

    private ViewpointEntity buildViewpoint(VisualizationInfo vi, byte[] snapshotData, ImageType snapshotType, int index, TopicEntity topic, TopicFolder folder) {
        ViewpointEntity entity = new ViewpointEntity();
        entity.setTopic(topic);
        entity.setGuid(vi.getGuid());
        entity.setIndex(index);
        entity.setPayload(buildViewpointPayload(vi));
        entity.setSnapshotData(snapshotData);
        entity.setSnapshotType(snapshotType);

        if (vi.getBitmaps() != null && vi.getBitmaps().getBitmap() != null) {
            for (Bitmap bitmap : vi.getBitmaps().getBitmap()) {
                entity.getBitmaps().add(buildBitmap(bitmap, folder, entity));
            }
        }

        return entity;
    }

    private String buildViewpointPayload(VisualizationInfo vi) {
        ObjectNode payload = objectMapper.createObjectNode();
        if (vi.getOrthogonalCamera() != null) payload.set("orthogonal_camera", objectMapper.valueToTree(vi.getOrthogonalCamera()));
        if (vi.getPerspectiveCamera() != null) payload.set("perspective_camera", objectMapper.valueToTree(vi.getPerspectiveCamera()));
        if (vi.getLines() != null) payload.set("lines", objectMapper.valueToTree(vi.getLines().getLine()));
        if (vi.getClippingPlanes() != null) payload.set("clipping_planes", objectMapper.valueToTree(vi.getClippingPlanes().getClippingPlane()));
        if (vi.getComponents() != null) payload.set("components", buildComponentsNode(vi.getComponents()));
        return payload.toString();
    }

    private ObjectNode buildComponentsNode(Components c) {
        ObjectNode node = objectMapper.createObjectNode();

        // Selection
        if(c.getSelection() != null) node.set("selection", objectMapper.valueToTree(c.getSelection().getComponent()));

        // Visibility
        if(c.getVisibility() != null) {
            ComponentVisibility vis = c.getVisibility();
            ObjectNode visNode = objectMapper.createObjectNode();
            visNode.put("default_visibility", vis.isDefaultVisibility());
            if(vis.getExceptions() != null) {
                visNode.set("exception", objectMapper.valueToTree(vis.getExceptions().getComponent()));
            } else {
                visNode.set("exception", objectMapper.createArrayNode());
            }
            // view setup hints
            if(vis.getViewSetupHints() != null) {
                ViewSetupHints hints = vis.getViewSetupHints();
                ObjectNode hintsNode = objectMapper.createObjectNode();
                hintsNode.put("space_visible", hints.isSpacesVisible());
                hintsNode.put("space_boundaries_visible", hints.isSpaceBoundariesVisible());
                hintsNode.put("opening_visible", hints.isOpeningsVisible());
                visNode.set("view_setup_hints", hintsNode);
            }
        node.set("visibility", visNode);
        }

        // Coloring
        if(c.getColoring() != null) {
            ArrayNode coloringArray = objectMapper.createArrayNode();
            for(ComponentColoring.Color color : c.getColoring().getColor()) {
                ObjectNode colorNode = objectMapper.createObjectNode();
                colorNode.put("color", color.getColor());
                colorNode.set("components", objectMapper.valueToTree(color.getComponents() != null ? color.getComponents().getComponent() : List.of()));
                coloringArray.add(colorNode);
            }
            node.set("coloring", coloringArray);
        }

        return node;
    }

    //----------------- Bitmap -----------------+

    private BitmapEntity buildBitmap(Bitmap bitmap, TopicFolder folder, ViewpointEntity viewpoint) {
        String ref = bitmap.getReference();
        byte[] data = ref != null ? folder.bitmaps().get(ref) : null;
        ImageType type = resolveImageType(ref);

        BitmapEntity entity = new BitmapEntity();
        entity.setViewpoint(viewpoint);
        entity.setGuid(extractGuidFromFilename(ref));
        entity.setBitmapData(data);
        entity.setBitmapType(type);
        entity.setBitmapMetadata(buildBitmapMetadata(bitmap));
        return entity;
    }

    private String buildBitmapMetadata(Bitmap bitmap) {
        ObjectNode meta = objectMapper.createObjectNode();
        meta.set("location", objectMapper.valueToTree(bitmap.getLocation()));
        meta.set("normal",   objectMapper.valueToTree(bitmap.getNormal()));
        meta.set("up",       objectMapper.valueToTree(bitmap.getUp()));
        meta.set("height",   objectMapper.valueToTree(bitmap.getHeight()));
        return meta.toString();
    }

    //----------------- FileEntity -----------------+

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
        fileEntity.setExternal(f.isIsExternal());
        project.getFiles().add(fileEntity);
        return fileEntity;
    }


    //----------------- BimSnippet -----------------+

    private BimSnippetEntity buildBimSnippet(BimSnippet snippet) {
        BimSnippetEntity entity = new BimSnippetEntity();
        entity.setSnippetType(snippet.getSnippetType());
        entity.setReference(snippet.getReference());
        entity.setReferenceSchema(snippet.getReferenceSchema());
        entity.setIsExternal(snippet.isIsExternal());
        return entity;
    }

    //----------------- DocumentReference -----------------+

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

    //----------------- Utils -----------------+

    private static ImageType resolveImageType(String filename) {
        if (filename == null) return null;
        return filename.toLowerCase().endsWith(".png") ? ImageType.PNG : ImageType.JPG;
    }

    private static final Pattern GUID_PATTERN = Pattern.compile("([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})");

    private static String extractGuidFromFilename(String filename) {
        if (filename == null) return UUID.randomUUID().toString();
        var matcher = GUID_PATTERN.matcher(filename);
        return matcher.find() ? matcher.group(1) : UUID.randomUUID().toString();
    }
}