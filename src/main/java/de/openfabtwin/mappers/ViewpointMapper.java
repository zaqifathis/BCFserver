package de.openfabtwin.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.openfabtwin.entities.BitmapEntity;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.generated.dto.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class ViewpointMapper {
    private final ObjectMapper objectMapper;

    public VisibilityGET toVisibilityDto(Visibility visibility) {
        VisibilityGET vg = new VisibilityGET();
        vg.setVisibility(visibility);
        return vg;
    }

    public ColoringGET toColoringDto(List<Coloring> coloring) {
        ColoringGET cl = new ColoringGET();
        cl.setColoring(coloring);
        return cl;
    }

    public SelectionGET toSelectionDto(List<de.openfabtwin.generated.dto.Component> component) {
        SelectionGET selection = new SelectionGET();
        selection.setSelection(component);
        return selection;
    }

    public ViewpointGET toDto(ViewpointEntity entity) {
        ViewpointGET dto = new ViewpointGET();
        dto.setGuid(entity.getGuid());
        dto.setIndex(entity.getIndex());

        // Snapshot (binary → Base64)
        if (entity.getSnapshotType() != null && entity.getSnapshotData() != null) {
            SnapshotGET snapshot = new SnapshotGET();
            snapshot.setSnapshotType(SnapshotGET.SnapshotTypeEnum.fromValue(entity.getSnapshotType().toApiValue()));
            dto.setSnapshot(snapshot);
        }
        // Bitmaps
        if (entity.getBitmaps() != null && !entity.getBitmaps().isEmpty()) {
            List<BitmapGET> bitmapsDto = new ArrayList<>();

            for (BitmapEntity bitmap : entity.getBitmaps()) {
                BitmapGET b = new BitmapGET();
                b.setGuid(bitmap.getGuid());
                b.setBitmapType(BitmapGET.BitmapTypeEnum.fromValue(bitmap.getBitmapType().toApiValue()));

                try {
                    JsonNode meta = objectMapper.readTree(bitmap.getBitmapMetadata());
                    b.setLocation(objectMapper.treeToValue(meta.get("location"), Location.class));
                    b.setNormal(objectMapper.treeToValue(meta.get("normal"), Direction.class));
                    b.setUp(objectMapper.treeToValue(meta.get("up"), Direction.class));
                    b.setHeight(BigDecimal.valueOf(meta.get("height").asDouble()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                bitmapsDto.add(b);
            }
            dto.setBitmaps(bitmapsDto);
        }

        // Parse payload JSON
        if (entity.getPayload() != null) {
            try {
                JsonNode payload = objectMapper.readTree(entity.getPayload());

                if (payload.has("orthogonal_camera")) {
                    OrthogonalCamera orthogonalCamera = objectMapper.treeToValue(payload.get("orthogonal_camera"), OrthogonalCamera.class);
                    dto.setOrthogonalCamera(orthogonalCamera);
                }
                if (payload.has("perspective_camera")) {
                    PerspectiveCamera perspectiveCamera = objectMapper.treeToValue(payload.get("perspective_camera"), PerspectiveCamera.class);
                    dto.setPerspectiveCamera(perspectiveCamera);
                }
                if (payload.has("lines")) {
                    dto.setLines(objectMapper.convertValue(payload.get("lines"), new TypeReference<List<Line>>() {})
                    );
                }
                if (payload.has("clipping_planes")) {
                    dto.setClippingPlanes(objectMapper.convertValue(payload.get("clipping_planes"), new TypeReference<List<ClippingPlane>>() {})
                    );
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse viewpoint payload", e);
            }
        }

        return dto;
    }
}
