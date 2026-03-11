package de.openfabtwin.services.bcfimport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.openfabtwin.entities.BitmapEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.generated.visinfo.*;
import de.openfabtwin.services.ViewpointService.ImageType;
import de.openfabtwin.utils.BcfZipReader.TopicFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class BcfViewpointBuilder {

    private final ObjectMapper objectMapper;

    private static final Pattern GUID_PATTERN = Pattern.compile(
            "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12})");

    public ViewpointEntity build(VisualizationInfo vi, byte[] snapshotData, ImageType snapshotType,
                                 int index, TopicEntity topic, TopicFolder folder) {
        ViewpointEntity entity = new ViewpointEntity();
        entity.setTopic(topic);
        entity.setGuid(vi.getGuid());
        entity.setIndex(index);
        entity.setPayload(buildPayload(vi));
        entity.setSnapshotData(snapshotData);
        entity.setSnapshotType(snapshotType);

        if (vi.getBitmaps() != null && vi.getBitmaps().getBitmap() != null) {
            for (Bitmap bitmap : vi.getBitmaps().getBitmap()) {
                entity.getBitmaps().add(buildBitmap(bitmap, folder, entity));
            }
        }

        return entity;
    }

    public static ImageType resolveImageType(String filename) {
        if (filename == null) return null;
        return filename.toLowerCase().endsWith(".png") ? ImageType.PNG : ImageType.JPG;
    }

    // -----------------

    private String buildPayload(VisualizationInfo vi) {
        ObjectNode payload = objectMapper.createObjectNode();
        if (vi.getOrthogonalCamera() != null)  payload.set("orthogonal_camera",  objectMapper.valueToTree(vi.getOrthogonalCamera()));
        if (vi.getPerspectiveCamera() != null) payload.set("perspective_camera", objectMapper.valueToTree(vi.getPerspectiveCamera()));
        if (vi.getLines() != null)             payload.set("lines",              objectMapper.valueToTree(vi.getLines().getLine()));
        if (vi.getClippingPlanes() != null)    payload.set("clipping_planes",    objectMapper.valueToTree(vi.getClippingPlanes().getClippingPlane()));
        if (vi.getComponents() != null)        payload.set("components",         buildComponentsNode(vi.getComponents()));
        return payload.toString();
    }

    private ObjectNode buildComponentsNode(Components c) {
        ObjectNode node = objectMapper.createObjectNode();

        if (c.getSelection() != null)
            node.set("selection", objectMapper.valueToTree(c.getSelection().getComponent()));

        if (c.getVisibility() != null) {
            ComponentVisibility vis = c.getVisibility();
            ObjectNode visNode = objectMapper.createObjectNode();
            visNode.put("default_visibility", vis.isDefaultVisibility());
            visNode.set("exception", vis.getExceptions() != null
                    ? objectMapper.valueToTree(vis.getExceptions().getComponent())
                    : objectMapper.createArrayNode());
            if (vis.getViewSetupHints() != null) {
                ViewSetupHints hints = vis.getViewSetupHints();
                ObjectNode hintsNode = objectMapper.createObjectNode();
                hintsNode.put("space_visible",            hints.isSpacesVisible());
                hintsNode.put("space_boundaries_visible", hints.isSpaceBoundariesVisible());
                hintsNode.put("opening_visible",          hints.isOpeningsVisible());
                visNode.set("view_setup_hints", hintsNode);
            }
            node.set("visibility", visNode);
        }

        if (c.getColoring() != null) {
            ArrayNode coloringArray = objectMapper.createArrayNode();
            for (ComponentColoring.Color color : c.getColoring().getColor()) {
                ObjectNode colorNode = objectMapper.createObjectNode();
                colorNode.put("color", color.getColor());
                colorNode.set("components", objectMapper.valueToTree(
                        color.getComponents() != null ? color.getComponents().getComponent() : List.of()));
                coloringArray.add(colorNode);
            }
            node.set("coloring", coloringArray);
        }

        return node;
    }

    private BitmapEntity buildBitmap(Bitmap bitmap, TopicFolder folder, ViewpointEntity viewpoint) {
        String ref = bitmap.getReference();
        BitmapEntity entity = new BitmapEntity();
        entity.setViewpoint(viewpoint);
        entity.setGuid(extractGuidFromFilename(ref));
        entity.setBitmapData(ref != null ? folder.bitmaps().get(ref) : null);
        entity.setBitmapType(resolveImageType(ref));
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

    private String extractGuidFromFilename(String filename) {
        if (filename == null) return UUID.randomUUID().toString();
        var matcher = GUID_PATTERN.matcher(filename);
        return matcher.find() ? matcher.group(1) : UUID.randomUUID().toString();
    }
}