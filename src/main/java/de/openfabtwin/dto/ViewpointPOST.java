package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ViewpointPOST
 */

@JsonTypeName("viewpoint_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ViewpointPOST {

  private JsonNullable<String> guid = JsonNullable.<String>undefined();

  private JsonNullable<Integer> index = JsonNullable.<Integer>undefined();

  private JsonNullable<OrthogonalCamera> orthogonalCamera = JsonNullable.<OrthogonalCamera>undefined();

  private JsonNullable<PerspectiveCamera> perspectiveCamera = JsonNullable.<PerspectiveCamera>undefined();

  @Valid
  private JsonNullable<List<@Valid Line>> lines = JsonNullable.<List<@Valid Line>>undefined();

  @Valid
  private JsonNullable<List<@Valid ClippingPlane>> clippingPlanes = JsonNullable.<List<@Valid ClippingPlane>>undefined();

  @Valid
  private JsonNullable<List<@Valid BitmapPOST>> bitmaps = JsonNullable.<List<@Valid BitmapPOST>>undefined();

  private JsonNullable<SnapshotPOST> snapshot = JsonNullable.<SnapshotPOST>undefined();

  private JsonNullable<Components> components = JsonNullable.<Components>undefined();

  public ViewpointPOST guid(String guid) {
    this.guid = JsonNullable.of(guid);
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("guid")
  public JsonNullable<String> getGuid() {
    return guid;
  }

  public void setGuid(JsonNullable<String> guid) {
    this.guid = guid;
  }

  public ViewpointPOST index(Integer index) {
    this.index = JsonNullable.of(index);
    return this;
  }

  /**
   * Get index
   * @return index
   */
  
  @Schema(name = "index", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("index")
  public JsonNullable<Integer> getIndex() {
    return index;
  }

  public void setIndex(JsonNullable<Integer> index) {
    this.index = index;
  }

  public ViewpointPOST orthogonalCamera(OrthogonalCamera orthogonalCamera) {
    this.orthogonalCamera = JsonNullable.of(orthogonalCamera);
    return this;
  }

  /**
   * Get orthogonalCamera
   * @return orthogonalCamera
   */
  @Valid 
  @Schema(name = "orthogonal_camera", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("orthogonal_camera")
  public JsonNullable<OrthogonalCamera> getOrthogonalCamera() {
    return orthogonalCamera;
  }

  public void setOrthogonalCamera(JsonNullable<OrthogonalCamera> orthogonalCamera) {
    this.orthogonalCamera = orthogonalCamera;
  }

  public ViewpointPOST perspectiveCamera(PerspectiveCamera perspectiveCamera) {
    this.perspectiveCamera = JsonNullable.of(perspectiveCamera);
    return this;
  }

  /**
   * Get perspectiveCamera
   * @return perspectiveCamera
   */
  @Valid 
  @Schema(name = "perspective_camera", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("perspective_camera")
  public JsonNullable<PerspectiveCamera> getPerspectiveCamera() {
    return perspectiveCamera;
  }

  public void setPerspectiveCamera(JsonNullable<PerspectiveCamera> perspectiveCamera) {
    this.perspectiveCamera = perspectiveCamera;
  }

  public ViewpointPOST lines(List<@Valid Line> lines) {
    this.lines = JsonNullable.of(lines);
    return this;
  }

  public ViewpointPOST addLinesItem(Line linesItem) {
    if (this.lines == null || !this.lines.isPresent()) {
      this.lines = JsonNullable.of(new ArrayList<>());
    }
    this.lines.get().add(linesItem);
    return this;
  }

  /**
   * Get lines
   * @return lines
   */
  @Valid 
  @Schema(name = "lines", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lines")
  public JsonNullable<List<@Valid Line>> getLines() {
    return lines;
  }

  public void setLines(JsonNullable<List<@Valid Line>> lines) {
    this.lines = lines;
  }

  public ViewpointPOST clippingPlanes(List<@Valid ClippingPlane> clippingPlanes) {
    this.clippingPlanes = JsonNullable.of(clippingPlanes);
    return this;
  }

  public ViewpointPOST addClippingPlanesItem(ClippingPlane clippingPlanesItem) {
    if (this.clippingPlanes == null || !this.clippingPlanes.isPresent()) {
      this.clippingPlanes = JsonNullable.of(new ArrayList<>());
    }
    this.clippingPlanes.get().add(clippingPlanesItem);
    return this;
  }

  /**
   * Get clippingPlanes
   * @return clippingPlanes
   */
  @Valid 
  @Schema(name = "clipping_planes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clipping_planes")
  public JsonNullable<List<@Valid ClippingPlane>> getClippingPlanes() {
    return clippingPlanes;
  }

  public void setClippingPlanes(JsonNullable<List<@Valid ClippingPlane>> clippingPlanes) {
    this.clippingPlanes = clippingPlanes;
  }

  public ViewpointPOST bitmaps(List<@Valid BitmapPOST> bitmaps) {
    this.bitmaps = JsonNullable.of(bitmaps);
    return this;
  }

  public ViewpointPOST addBitmapsItem(BitmapPOST bitmapsItem) {
    if (this.bitmaps == null || !this.bitmaps.isPresent()) {
      this.bitmaps = JsonNullable.of(new ArrayList<>());
    }
    this.bitmaps.get().add(bitmapsItem);
    return this;
  }

  /**
   * Get bitmaps
   * @return bitmaps
   */
  @Valid 
  @Schema(name = "bitmaps", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bitmaps")
  public JsonNullable<List<@Valid BitmapPOST>> getBitmaps() {
    return bitmaps;
  }

  public void setBitmaps(JsonNullable<List<@Valid BitmapPOST>> bitmaps) {
    this.bitmaps = bitmaps;
  }

  public ViewpointPOST snapshot(SnapshotPOST snapshot) {
    this.snapshot = JsonNullable.of(snapshot);
    return this;
  }

  /**
   * Get snapshot
   * @return snapshot
   */
  @Valid 
  @Schema(name = "snapshot", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("snapshot")
  public JsonNullable<SnapshotPOST> getSnapshot() {
    return snapshot;
  }

  public void setSnapshot(JsonNullable<SnapshotPOST> snapshot) {
    this.snapshot = snapshot;
  }

  public ViewpointPOST components(Components components) {
    this.components = JsonNullable.of(components);
    return this;
  }

  /**
   * Get components
   * @return components
   */
  @Valid 
  @Schema(name = "components", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("components")
  public JsonNullable<Components> getComponents() {
    return components;
  }

  public void setComponents(JsonNullable<Components> components) {
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
    return equalsNullable(this.guid, viewpointPOST.guid) &&
        equalsNullable(this.index, viewpointPOST.index) &&
        equalsNullable(this.orthogonalCamera, viewpointPOST.orthogonalCamera) &&
        equalsNullable(this.perspectiveCamera, viewpointPOST.perspectiveCamera) &&
        equalsNullable(this.lines, viewpointPOST.lines) &&
        equalsNullable(this.clippingPlanes, viewpointPOST.clippingPlanes) &&
        equalsNullable(this.bitmaps, viewpointPOST.bitmaps) &&
        equalsNullable(this.snapshot, viewpointPOST.snapshot) &&
        equalsNullable(this.components, viewpointPOST.components);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(guid), hashCodeNullable(index), hashCodeNullable(orthogonalCamera), hashCodeNullable(perspectiveCamera), hashCodeNullable(lines), hashCodeNullable(clippingPlanes), hashCodeNullable(bitmaps), hashCodeNullable(snapshot), hashCodeNullable(components));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
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
    
    public ViewpointPOST.Builder guid(JsonNullable<String> guid) {
      this.instance.guid = guid;
      return this;
    }
    
    public ViewpointPOST.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public ViewpointPOST.Builder index(JsonNullable<Integer> index) {
      this.instance.index = index;
      return this;
    }
    
    public ViewpointPOST.Builder orthogonalCamera(OrthogonalCamera orthogonalCamera) {
      this.instance.orthogonalCamera(orthogonalCamera);
      return this;
    }
    
    public ViewpointPOST.Builder orthogonalCamera(JsonNullable<OrthogonalCamera> orthogonalCamera) {
      this.instance.orthogonalCamera = orthogonalCamera;
      return this;
    }
    
    public ViewpointPOST.Builder perspectiveCamera(PerspectiveCamera perspectiveCamera) {
      this.instance.perspectiveCamera(perspectiveCamera);
      return this;
    }
    
    public ViewpointPOST.Builder perspectiveCamera(JsonNullable<PerspectiveCamera> perspectiveCamera) {
      this.instance.perspectiveCamera = perspectiveCamera;
      return this;
    }
    
    public ViewpointPOST.Builder lines(List<Line> lines) {
      this.instance.lines(lines);
      return this;
    }
    
    public ViewpointPOST.Builder lines(JsonNullable<List<Line>> lines) {
      this.instance.lines = lines;
      return this;
    }
    
    public ViewpointPOST.Builder clippingPlanes(List<ClippingPlane> clippingPlanes) {
      this.instance.clippingPlanes(clippingPlanes);
      return this;
    }
    
    public ViewpointPOST.Builder clippingPlanes(JsonNullable<List<ClippingPlane>> clippingPlanes) {
      this.instance.clippingPlanes = clippingPlanes;
      return this;
    }
    
    public ViewpointPOST.Builder bitmaps(List<BitmapPOST> bitmaps) {
      this.instance.bitmaps(bitmaps);
      return this;
    }
    
    public ViewpointPOST.Builder bitmaps(JsonNullable<List<BitmapPOST>> bitmaps) {
      this.instance.bitmaps = bitmaps;
      return this;
    }
    
    public ViewpointPOST.Builder snapshot(SnapshotPOST snapshot) {
      this.instance.snapshot(snapshot);
      return this;
    }
    
    public ViewpointPOST.Builder snapshot(JsonNullable<SnapshotPOST> snapshot) {
      this.instance.snapshot = snapshot;
      return this;
    }
    
    public ViewpointPOST.Builder components(Components components) {
      this.instance.components(components);
      return this;
    }
    
    public ViewpointPOST.Builder components(JsonNullable<Components> components) {
      this.instance.components = components;
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

