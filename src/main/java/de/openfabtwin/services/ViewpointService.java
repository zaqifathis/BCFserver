package de.openfabtwin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.openfabtwin.entities.BitmapEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.exceptions.ConflictException;
import de.openfabtwin.generated.dto.*;
import de.openfabtwin.generated.visinfo.VisualizationInfo;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.repositories.ViewpointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ViewpointService {

    private static final Pattern GUID_PATTERN = Pattern.compile(
            "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})");

    private final ViewpointRepository viewpointRepository;
    private final CommentRepository commentRepository;
    private final EntityResolver entityResolver;
    private final ObjectMapper objectMapper;

    public ViewpointEntity create(String projectId, String topicId, ViewpointPOST viewpointPOST) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);

        validateViewpointPOST(viewpointPOST);

        String guid = viewpointPOST.getGuid();
        if (guid != null) {
            boolean isExisting = viewpointRepository.existsByGuid(guid);
            if (isExisting) {
                throw new ConflictException("Viewpoint with GUID " + guid + " already exists");
            }
        }
        else { guid = UUID.randomUUID().toString(); }

        ObjectNode payload = objectMapper.createObjectNode();
        if (viewpointPOST.getOrthogonalCamera() != null) {
            payload.set("orthogonal_camera", objectMapper.valueToTree(viewpointPOST.getOrthogonalCamera()));
        }
        if (viewpointPOST.getPerspectiveCamera() != null) {
            payload.set("perspective_camera", objectMapper.valueToTree(viewpointPOST.getPerspectiveCamera()));
        }
        if (viewpointPOST.getLines() != null) {
            payload.set("lines", objectMapper.valueToTree(viewpointPOST.getLines()));
        }
        if (viewpointPOST.getClippingPlanes() != null) {payload.set("clipping_planes", objectMapper.valueToTree(viewpointPOST.getClippingPlanes()));
        }
        if (viewpointPOST.getComponents() != null) {payload.set("components", objectMapper.valueToTree(viewpointPOST.getComponents()));
        }

        ViewpointEntity viewpointEntity = new ViewpointEntity();
        viewpointEntity.setGuid(guid);
        viewpointEntity.setIndex(viewpointPOST.getIndex());
        viewpointEntity.setPayload(payload.toString());
        viewpointEntity.setTopic(topic);

        // Store snapshot if present
        if (viewpointPOST.getSnapshot() != null) {
            SnapshotPOST snapshot = viewpointPOST.getSnapshot();
            if (snapshot.getSnapshotType() != null) {
                viewpointEntity.setSnapshotType(ImageType.fromApiValue(snapshot.getSnapshotType().getValue()));
            }
            viewpointEntity.setSnapshotData(Base64.getDecoder().decode(snapshot.getSnapshotData()));
        }

        //store bitmaps if present
        if (viewpointPOST.getBitmaps() != null) {
            for (BitmapPOST bitmap : viewpointPOST.getBitmaps()) {
                BitmapEntity bitmapEntity = new BitmapEntity();
                bitmapEntity.setGuid(UUID.randomUUID().toString());
                if (bitmap.getBitmapType() != null) {
                    bitmapEntity.setBitmapType(ImageType.fromApiValue(bitmap.getBitmapType().getValue()));
                }
                bitmapEntity.setBitmapData(Base64.getDecoder().decode(bitmap.getBitmapData()));

                ObjectNode meta = objectMapper.createObjectNode();
                meta.set("location", objectMapper.valueToTree(bitmap.getLocation()));
                meta.set("normal", objectMapper.valueToTree(bitmap.getNormal()));
                meta.set("up", objectMapper.valueToTree(bitmap.getUp()));
                meta.set("height", objectMapper.valueToTree(bitmap.getHeight()));

                bitmapEntity.setBitmapMetadata(meta.toString());
                bitmapEntity.setViewpoint(viewpointEntity);
                viewpointEntity.getBitmaps().add(bitmapEntity);
            }
        }
        return viewpointRepository.save(viewpointEntity);
    }

    /**
     * Builds a viewpoint entity from a parsed BCF {@code .bcfv} (visinfo.xsd) file.
     * The entity is not saved: it is attached to the topic and persisted through cascade.
     *
     * @param bitmapFiles bitmap image bytes of the topic folder, keyed by file name
     */
    public ViewpointEntity createFromVisualizationInfo(VisualizationInfo vi, byte[] snapshotData, ImageType snapshotType,
                                                       int index, TopicEntity topic, Map<String, byte[]> bitmapFiles) {
        ViewpointEntity entity = new ViewpointEntity();
        entity.setTopic(topic);
        entity.setGuid(vi.getGuid());
        entity.setIndex(index);
        entity.setPayload(toPayload(vi));
        entity.setSnapshotData(snapshotData);
        entity.setSnapshotType(snapshotType);

        if (vi.getBitmaps() != null && vi.getBitmaps().getBitmap() != null) {
            for (de.openfabtwin.generated.visinfo.Bitmap bitmap : vi.getBitmaps().getBitmap()) {
                entity.getBitmaps().add(toBitmapEntity(bitmap, bitmapFiles, entity));
            }
        }
        return entity;
    }

    // Payload uses the DTO shape (snake_case) so that ViewpointMapper can read it back
    private String toPayload(VisualizationInfo vi) {
        ObjectNode payload = objectMapper.createObjectNode();
        if (vi.getOrthogonalCamera() != null) {
            var cam = vi.getOrthogonalCamera();
            OrthogonalCamera dto = new OrthogonalCamera();
            dto.setCameraViewPoint(toPoint(cam.getCameraViewPoint()));
            dto.setCameraDirection(toDirection(cam.getCameraDirection()));
            dto.setCameraUpVector(toDirection(cam.getCameraUpVector()));
            dto.setViewToWorldScale(BigDecimal.valueOf(cam.getViewToWorldScale()));
            dto.setAspectRatio(BigDecimal.valueOf(cam.getAspectRatio()));
            payload.set("orthogonal_camera", objectMapper.valueToTree(dto));
        }
        if (vi.getPerspectiveCamera() != null) {
            var cam = vi.getPerspectiveCamera();
            PerspectiveCamera dto = new PerspectiveCamera();
            dto.setCameraViewPoint(toPoint(cam.getCameraViewPoint()));
            dto.setCameraDirection(toDirection(cam.getCameraDirection()));
            dto.setCameraUpVector(toDirection(cam.getCameraUpVector()));
            dto.setFieldOfView(BigDecimal.valueOf(cam.getFieldOfView()));
            dto.setAspectRatio(BigDecimal.valueOf(cam.getAspectRatio()));
            payload.set("perspective_camera", objectMapper.valueToTree(dto));
        }
        if (vi.getLines() != null) {
            List<Line> lines = new ArrayList<>();
            for (var l : vi.getLines().getLine()) {
                Line dto = new Line();
                dto.setStartPoint(toPoint(l.getStartPoint()));
                dto.setEndPoint(toPoint(l.getEndPoint()));
                lines.add(dto);
            }
            payload.set("lines", objectMapper.valueToTree(lines));
        }
        if (vi.getClippingPlanes() != null) {
            List<ClippingPlane> planes = new ArrayList<>();
            for (var p : vi.getClippingPlanes().getClippingPlane()) {
                ClippingPlane dto = new ClippingPlane();
                dto.setLocation(toLocation(p.getLocation()));
                dto.setDirection(toDirection(p.getDirection()));
                planes.add(dto);
            }
            payload.set("clipping_planes", objectMapper.valueToTree(planes));
        }
        if (vi.getComponents() != null) {
            payload.set("components", objectMapper.valueToTree(toComponents(vi.getComponents())));
        }
        return payload.toString();
    }

    private Components toComponents(de.openfabtwin.generated.visinfo.Components c) {
        Components dto = new Components();

        if (c.getSelection() != null) {
            dto.setSelection(toComponentList(c.getSelection().getComponent()));
        }

        if (c.getVisibility() != null) {
            var vis = c.getVisibility();
            Visibility visDto = new Visibility();
            visDto.setDefaultVisibility(vis.isDefaultVisibility());
            visDto.setExceptions(vis.getExceptions() != null
                    ? toComponentList(vis.getExceptions().getComponent())
                    : new ArrayList<>());
            if (vis.getViewSetupHints() != null) {
                var hints = vis.getViewSetupHints();
                ViewSetupHints hintsDto = new ViewSetupHints();
                hintsDto.setSpacesVisible(hints.isSpacesVisible());
                hintsDto.setSpaceBoundariesVisible(hints.isSpaceBoundariesVisible());
                hintsDto.setOpeningsVisible(hints.isOpeningsVisible());
                visDto.setViewSetupHints(hintsDto);
            }
            dto.setVisibility(visDto);
        }

        if (c.getColoring() != null) {
            List<Coloring> colorings = new ArrayList<>();
            for (var color : c.getColoring().getColor()) {
                Coloring colorDto = new Coloring();
                colorDto.setColor(color.getColor());
                colorDto.setComponents(color.getComponents() != null
                        ? toComponentList(color.getComponents().getComponent())
                        : new ArrayList<>());
                colorings.add(colorDto);
            }
            dto.setColoring(colorings);
        }
        return dto;
    }

    private List<Component> toComponentList(List<de.openfabtwin.generated.visinfo.Component> components) {
        List<Component> result = new ArrayList<>();
        for (var c : components) {
            Component dto = new Component();
            dto.setIfcGuid(c.getIfcGuid());
            dto.setOriginatingSystem(c.getOriginatingSystem());
            dto.setAuthoringToolId(c.getAuthoringToolId());
            result.add(dto);
        }
        return result;
    }

    private BitmapEntity toBitmapEntity(de.openfabtwin.generated.visinfo.Bitmap bitmap, Map<String, byte[]> bitmapFiles,
                                        ViewpointEntity viewpoint) {
        String ref = bitmap.getReference();
        BitmapEntity entity = new BitmapEntity();
        entity.setViewpoint(viewpoint);
        entity.setGuid(extractGuidFromFilename(ref));
        entity.setBitmapData(ref != null ? bitmapFiles.get(ref) : null);
        entity.setBitmapType(ImageType.fromFilename(ref));

        ObjectNode meta = objectMapper.createObjectNode();
        meta.set("location", objectMapper.valueToTree(toLocation(bitmap.getLocation())));
        meta.set("normal",   objectMapper.valueToTree(toDirection(bitmap.getNormal())));
        meta.set("up",       objectMapper.valueToTree(toDirection(bitmap.getUp())));
        meta.set("height",   objectMapper.valueToTree(BigDecimal.valueOf(bitmap.getHeight())));
        entity.setBitmapMetadata(meta.toString());
        return entity;
    }

    private Point toPoint(de.openfabtwin.generated.visinfo.Point p) {
        return new Point().x(BigDecimal.valueOf(p.getX())).y(BigDecimal.valueOf(p.getY())).z(BigDecimal.valueOf(p.getZ()));
    }

    private Location toLocation(de.openfabtwin.generated.visinfo.Point p) {
        return new Location().x(BigDecimal.valueOf(p.getX())).y(BigDecimal.valueOf(p.getY())).z(BigDecimal.valueOf(p.getZ()));
    }

    private Direction toDirection(de.openfabtwin.generated.visinfo.Direction d) {
        return new Direction().x(BigDecimal.valueOf(d.getX())).y(BigDecimal.valueOf(d.getY())).z(BigDecimal.valueOf(d.getZ()));
    }

    private String extractGuidFromFilename(String filename) {
        if (filename == null) return UUID.randomUUID().toString();
        var matcher = GUID_PATTERN.matcher(filename);
        return matcher.find() ? matcher.group(1) : UUID.randomUUID().toString();
    }

    private void validateViewpointPOST(ViewpointPOST viewpointPOST) {
        boolean hasPerspective = viewpointPOST.getPerspectiveCamera() != null;
        boolean hasOrthogonal = viewpointPOST.getOrthogonalCamera() != null;
        boolean hasCamera = hasPerspective || hasOrthogonal;
        boolean hasSnapshot = viewpointPOST.getSnapshot() != null;
        boolean hasVizInfo =
                viewpointPOST.getLines() != null ||
                        viewpointPOST.getClippingPlanes() != null ||
                        viewpointPOST.getBitmaps() != null ||
                        viewpointPOST.getComponents() != null;

        if(hasPerspective && hasOrthogonal) {
            throw new IllegalArgumentException("Viewpoint cannot have both camera definition. Either perspective or orthogonal camera");
        }
        if(!hasCamera && !hasSnapshot) {
            throw new IllegalArgumentException("Viewpoint must contain a camera definition, a snapshot or both");
        }
        if (hasVizInfo && !hasCamera) {
            throw new IllegalArgumentException("Viewpoint with lines, clipping planes, bitmaps or components must contain a camera definition");
        }
    }


    public ImageResult getSnapshotResource(String projectId, String topicId, String viewpointId) {
        ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, viewpointId);

        if (vp.getSnapshotType() == null || vp.getSnapshotData() == null) {
            throw new EntityNotFoundException("Snapshot not found for viewpoint " + viewpointId);
        }
        return new ImageResult(vp.getSnapshotType(),  vp.getSnapshotData());
    }

    public ImageResult getBitmapResource(String projectId, String topicId, String viewpointId, String bitmapId) {
        BitmapEntity bitmap = entityResolver.resolveBitmap(viewpointId, bitmapId);
        if (bitmap.getBitmapType() == null || bitmap.getBitmapData() == null) {
            throw new EntityNotFoundException("Bitmap data not found for bitmap " + bitmapId);
        }
        return new ImageResult(bitmap.getBitmapType(), bitmap.getBitmapData());
    }

    public ViewpointEntity getById(String projectId, String topicId, String viewpointId) {
        return entityResolver.resolveViewpoint(projectId, topicId, viewpointId);
    }

    public Collection<ViewpointEntity> getAll(String projectId, String topicId) {
        entityResolver.resolveTopic(projectId, topicId);
        return viewpointRepository.findAllByTopic_Guid(topicId);
    }

    public List<Component> getSelectionComponents(String projectId, String topicId, String viewpointId) {
        ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, viewpointId);
        JsonNode selectionNode = getPayloadNode(vp, "selection");
        if (selectionNode == null || selectionNode.isEmpty()) return List.of();
        return objectMapper.convertValue(selectionNode, new TypeReference<List<Component>>() {});
    }

    public List<Coloring> getColoring(String projectId, String topicId, String viewpointId) {
        ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, viewpointId);
        JsonNode coloringNode = getPayloadNode(vp, "coloring");
        if (coloringNode == null || coloringNode.isEmpty()) return List.of();
        return objectMapper.convertValue(coloringNode, new TypeReference<List<Coloring>>() {});
    }

    public Visibility getVisibility(String projectId, String topicId, String viewpointId) {
        ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, viewpointId);
        JsonNode visibilityNode = getPayloadNode(vp, "visibility");
        if (visibilityNode == null || visibilityNode.isEmpty()) return null;
        return objectMapper.convertValue(visibilityNode, Visibility.class);
    }

    private JsonNode getPayloadNode(ViewpointEntity vp, String nodeName) {
        if (vp.getPayload() == null) return null;
        try {
            JsonNode payload = objectMapper.readTree(vp.getPayload());
            JsonNode componentsNode = payload.get("components");
            if (componentsNode == null || !componentsNode.has(nodeName)) return null;

            return payload
                    .path("components")
                    .path(nodeName);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to parse", e);
        }
    }

    public void deleteById(String projectId, String topicId, String viewpointId) {
        ViewpointEntity vp = entityResolver.resolveViewpoint(projectId, topicId, viewpointId);
        boolean hasComments = commentRepository.existsByViewpoint_Guid(viewpointId);
        if (hasComments) {
            throw new ConflictException("Cannot delete viewpoint " + viewpointId + " because it has comments associated");
        } else {
            viewpointRepository.delete(vp);
        }
    }

    public enum ImageType {
        PNG, JPG;

        public static ImageType fromApiValue(String value) {
            return ImageType.valueOf(value.toUpperCase());
        }

        public static ImageType fromFilename(String filename) {
            if (filename == null) return null;
            return filename.toLowerCase().endsWith(".png") ? PNG : JPG;
        }

        public String toApiValue() {
            return this.name().toLowerCase();
        }
    }
}

