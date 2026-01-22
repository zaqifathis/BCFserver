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
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.repositories.ViewpointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ViewpointService {

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

        public String toApiValue() {
            return this.name().toLowerCase();
        }
    }
}

