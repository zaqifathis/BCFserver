package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ViewpointPOST
 */

@JsonTypeName("viewpoint_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ViewpointPOST {

  private @Nullable String guid = null;

  private @Nullable Integer index = null;

  private @Nullable OrthogonalCamera orthogonalCamera = null;

  private @Nullable PerspectiveCamera perspectiveCamera = null;

  @Valid
  private @Nullable List<@Valid Line> lines;

  @Valid
  private @Nullable List<@Valid ClippingPlane> clippingPlanes;

  @Valid
  private @Nullable List<@Valid BitmapPOST> bitmaps;

  private @Nullable SnapshotPOST snapshot = null;

  private @Nullable Components components = null;

  public ViewpointPOST guid(@Nullable String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("guid")
  public @Nullable String getGuid() {
    return guid;
  }

  public void setGuid(@Nullable String guid) {
    this.guid = guid;
  }

  public ViewpointPOST index(@Nullable Integer index) {
    this.index = index;
    return this;
  }

  /**
   * Get index
   * @return index
   */
  
  @Schema(name = "index", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("index")
  public @Nullable Integer getIndex() {
    return index;
  }

  public void setIndex(@Nullable Integer index) {
    this.index = index;
  }

  public ViewpointPOST orthogonalCamera(@Nullable OrthogonalCamera orthogonalCamera) {
    this.orthogonalCamera = orthogonalCamera;
    return this;
  }

  /**
   * Get orthogonalCamera
   * @return orthogonalCamera
   */
  @Valid 
  @Schema(name = "orthogonal_camera", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("orthogonal_camera")
  public @Nullable OrthogonalCamera getOrthogonalCamera() {
    return orthogonalCamera;
  }

  public void setOrthogonalCamera(@Nullable OrthogonalCamera orthogonalCamera) {
    this.orthogonalCamera = orthogonalCamera;
  }

  public ViewpointPOST perspectiveCamera(@Nullable PerspectiveCamera perspectiveCamera) {
    this.perspectiveCamera = perspectiveCamera;
    return this;
  }

  /**
   * Get perspectiveCamera
   * @return perspectiveCamera
   */
  @Valid 
  @Schema(name = "perspective_camera", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("perspective_camera")
  public @Nullable PerspectiveCamera getPerspectiveCamera() {
    return perspectiveCamera;
  }

  public void setPerspectiveCamera(@Nullable PerspectiveCamera perspectiveCamera) {
    this.perspectiveCamera = perspectiveCamera;
  }

  public ViewpointPOST lines(@Nullable List<@Valid Line> lines) {
    this.lines = lines;
    return this;
  }

  public ViewpointPOST addLinesItem(Line linesItem) {
    if (this.lines == null) {
      this.lines = new ArrayList<>();
    }
    this.lines.add(linesItem);
    return this;
  }

  /**
   * Get lines
   * @return lines
   */
  @Valid 
  @Schema(name = "lines", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lines")
  public @Nullable List<@Valid Line> getLines() {
    return lines;
  }

  public void setLines(@Nullable List<@Valid Line> lines) {
    this.lines = lines;
  }

  public ViewpointPOST clippingPlanes(@Nullable List<@Valid ClippingPlane> clippingPlanes) {
    this.clippingPlanes = clippingPlanes;
    return this;
  }

  public ViewpointPOST addClippingPlanesItem(ClippingPlane clippingPlanesItem) {
    if (this.clippingPlanes == null) {
      this.clippingPlanes = new ArrayList<>();
    }
    this.clippingPlanes.add(clippingPlanesItem);
    return this;
  }

  /**
   * Get clippingPlanes
   * @return clippingPlanes
   */
  @Valid 
  @Schema(name = "clipping_planes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clipping_planes")
  public @Nullable List<@Valid ClippingPlane> getClippingPlanes() {
    return clippingPlanes;
  }

  public void setClippingPlanes(@Nullable List<@Valid ClippingPlane> clippingPlanes) {
    this.clippingPlanes = clippingPlanes;
  }

  public ViewpointPOST bitmaps(@Nullable List<@Valid BitmapPOST> bitmaps) {
    this.bitmaps = bitmaps;
    return this;
  }

  public ViewpointPOST addBitmapsItem(BitmapPOST bitmapsItem) {
    if (this.bitmaps == null) {
      this.bitmaps = new ArrayList<>();
    }
    this.bitmaps.add(bitmapsItem);
    return this;
  }

  /**
   * Get bitmaps
   * @return bitmaps
   */
  @Valid 
  @Schema(name = "bitmaps", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bitmaps")
  public @Nullable List<@Valid BitmapPOST> getBitmaps() {
    return bitmaps;
  }

  public void setBitmaps(@Nullable List<@Valid BitmapPOST> bitmaps) {
    this.bitmaps = bitmaps;
  }

  public ViewpointPOST snapshot(@Nullable SnapshotPOST snapshot) {
    this.snapshot = snapshot;
    return this;
  }

  /**
   * Get snapshot
   * @return snapshot
   */
  @Valid 
  @Schema(name = "snapshot", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("snapshot")
  public @Nullable SnapshotPOST getSnapshot() {
    return snapshot;
  }

  public void setSnapshot(@Nullable SnapshotPOST snapshot) {
    this.snapshot = snapshot;
  }

  public ViewpointPOST components(@Nullable Components components) {
    this.components = components;
    return this;
  }

  /**
   * Get components
   * @return components
   */
  @Valid 
  @Schema(name = "components", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("components")
  public @Nullable Components getComponents() {
    return components;
  }

  public void setComponents(@Nullable Components components) {
    this.components = components;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewpointPOST viewpointPOST = (ViewpointPOST) o;
    return Objects.equals(this.guid, viewpointPOST.guid) &&
        Objects.equals(this.index, viewpointPOST.index) &&
        Objects.equals(this.orthogonalCamera, viewpointPOST.orthogonalCamera) &&
        Objects.equals(this.perspectiveCamera, viewpointPOST.perspectiveCamera) &&
        Objects.equals(this.lines, viewpointPOST.lines) &&
        Objects.equals(this.clippingPlanes, viewpointPOST.clippingPlanes) &&
        Objects.equals(this.bitmaps, viewpointPOST.bitmaps) &&
        Objects.equals(this.snapshot, viewpointPOST.snapshot) &&
        Objects.equals(this.components, viewpointPOST.components);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, index, orthogonalCamera, perspectiveCamera, lines, clippingPlanes, bitmaps, snapshot, components);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViewpointPOST {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    orthogonalCamera: ").append(toIndentedString(orthogonalCamera)).append("\n");
    sb.append("    perspectiveCamera: ").append(toIndentedString(perspectiveCamera)).append("\n");
    sb.append("    lines: ").append(toIndentedString(lines)).append("\n");
    sb.append("    clippingPlanes: ").append(toIndentedString(clippingPlanes)).append("\n");
    sb.append("    bitmaps: ").append(toIndentedString(bitmaps)).append("\n");
    sb.append("    snapshot: ").append(toIndentedString(snapshot)).append("\n");
    sb.append("    components: ").append(toIndentedString(components)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private ViewpointPOST instance;

    public Builder() {
      this(new ViewpointPOST());
    }

    protected Builder(ViewpointPOST instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ViewpointPOST value) { 
      this.instance.setGuid(value.guid);
      this.instance.setIndex(value.index);
      this.instance.setOrthogonalCamera(value.orthogonalCamera);
      this.instance.setPerspectiveCamera(value.perspectiveCamera);
      this.instance.setLines(value.lines);
      this.instance.setClippingPlanes(value.clippingPlanes);
      this.instance.setBitmaps(value.bitmaps);
      this.instance.setSnapshot(value.snapshot);
      this.instance.setComponents(value.components);
      return this;
    }

    public ViewpointPOST.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public ViewpointPOST.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public ViewpointPOST.Builder orthogonalCamera(OrthogonalCamera orthogonalCamera) {
      this.instance.orthogonalCamera(orthogonalCamera);
      return this;
    }
    
    public ViewpointPOST.Builder perspectiveCamera(PerspectiveCamera perspectiveCamera) {
      this.instance.perspectiveCamera(perspectiveCamera);
      return this;
    }
    
    public ViewpointPOST.Builder lines(List<Line> lines) {
      this.instance.lines(lines);
      return this;
    }
    
    public ViewpointPOST.Builder clippingPlanes(List<ClippingPlane> clippingPlanes) {
      this.instance.clippingPlanes(clippingPlanes);
      return this;
    }
    
    public ViewpointPOST.Builder bitmaps(List<BitmapPOST> bitmaps) {
      this.instance.bitmaps(bitmaps);
      return this;
    }
    
    public ViewpointPOST.Builder snapshot(SnapshotPOST snapshot) {
      this.instance.snapshot(snapshot);
      return this;
    }
    
    public ViewpointPOST.Builder components(Components components) {
      this.instance.components(components);
      return this;
    }
    
    /**
    * returns a built ViewpointPOST instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ViewpointPOST build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static ViewpointPOST.Builder builder() {
    return new ViewpointPOST.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ViewpointPOST.Builder toBuilder() {
    ViewpointPOST.Builder builder = new ViewpointPOST.Builder();
    return builder.copyOf(this);
  }

}

