package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ViewpointGET
 */

@JsonTypeName("viewpoint_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ViewpointGET {

  private JsonNullable<Integer> index = JsonNullable.<Integer>undefined();

  private String guid;

  private JsonNullable<OrthogonalCamera> orthogonalCamera = JsonNullable.<OrthogonalCamera>undefined();

  private JsonNullable<PerspectiveCamera> perspectiveCamera = JsonNullable.<PerspectiveCamera>undefined();

  @Valid
  private JsonNullable<List<@Valid Line>> lines = JsonNullable.<List<@Valid Line>>undefined();

  @Valid
  private JsonNullable<List<@Valid ClippingPlane>> clippingPlanes = JsonNullable.<List<@Valid ClippingPlane>>undefined();

  @Valid
  private JsonNullable<List<@Valid BitmapGET>> bitmaps = JsonNullable.<List<@Valid BitmapGET>>undefined();

  private JsonNullable<SnapshotGET> snapshot = JsonNullable.<SnapshotGET>undefined();

  private @Nullable ViewpointGETAuthorization authorization;

  public ViewpointGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ViewpointGET(String guid) {
    this.guid = guid;
  }

  public ViewpointGET index(Integer index) {
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

  public ViewpointGET guid(String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  @NotNull 
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("guid")
  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public ViewpointGET orthogonalCamera(OrthogonalCamera orthogonalCamera) {
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

  public ViewpointGET perspectiveCamera(PerspectiveCamera perspectiveCamera) {
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

  public ViewpointGET lines(List<@Valid Line> lines) {
    this.lines = JsonNullable.of(lines);
    return this;
  }

  public ViewpointGET addLinesItem(Line linesItem) {
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

  public ViewpointGET clippingPlanes(List<@Valid ClippingPlane> clippingPlanes) {
    this.clippingPlanes = JsonNullable.of(clippingPlanes);
    return this;
  }

  public ViewpointGET addClippingPlanesItem(ClippingPlane clippingPlanesItem) {
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

  public ViewpointGET bitmaps(List<@Valid BitmapGET> bitmaps) {
    this.bitmaps = JsonNullable.of(bitmaps);
    return this;
  }

  public ViewpointGET addBitmapsItem(BitmapGET bitmapsItem) {
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
  public JsonNullable<List<@Valid BitmapGET>> getBitmaps() {
    return bitmaps;
  }

  public void setBitmaps(JsonNullable<List<@Valid BitmapGET>> bitmaps) {
    this.bitmaps = bitmaps;
  }

  public ViewpointGET snapshot(SnapshotGET snapshot) {
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
  public JsonNullable<SnapshotGET> getSnapshot() {
    return snapshot;
  }

  public void setSnapshot(JsonNullable<SnapshotGET> snapshot) {
    this.snapshot = snapshot;
  }

  public ViewpointGET authorization(@Nullable ViewpointGETAuthorization authorization) {
    this.authorization = authorization;
    return this;
  }

  /**
   * Get authorization
   * @return authorization
   */
  @Valid 
  @Schema(name = "authorization", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorization")
  public @Nullable ViewpointGETAuthorization getAuthorization() {
    return authorization;
  }

  public void setAuthorization(@Nullable ViewpointGETAuthorization authorization) {
    this.authorization = authorization;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewpointGET viewpointGET = (ViewpointGET) o;
    return equalsNullable(this.index, viewpointGET.index) &&
        Objects.equals(this.guid, viewpointGET.guid) &&
        equalsNullable(this.orthogonalCamera, viewpointGET.orthogonalCamera) &&
        equalsNullable(this.perspectiveCamera, viewpointGET.perspectiveCamera) &&
        equalsNullable(this.lines, viewpointGET.lines) &&
        equalsNullable(this.clippingPlanes, viewpointGET.clippingPlanes) &&
        equalsNullable(this.bitmaps, viewpointGET.bitmaps) &&
        equalsNullable(this.snapshot, viewpointGET.snapshot) &&
        Objects.equals(this.authorization, viewpointGET.authorization);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(index), guid, hashCodeNullable(orthogonalCamera), hashCodeNullable(perspectiveCamera), hashCodeNullable(lines), hashCodeNullable(clippingPlanes), hashCodeNullable(bitmaps), hashCodeNullable(snapshot), authorization);
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
    sb.append("class ViewpointGET {\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    orthogonalCamera: ").append(toIndentedString(orthogonalCamera)).append("\n");
    sb.append("    perspectiveCamera: ").append(toIndentedString(perspectiveCamera)).append("\n");
    sb.append("    lines: ").append(toIndentedString(lines)).append("\n");
    sb.append("    clippingPlanes: ").append(toIndentedString(clippingPlanes)).append("\n");
    sb.append("    bitmaps: ").append(toIndentedString(bitmaps)).append("\n");
    sb.append("    snapshot: ").append(toIndentedString(snapshot)).append("\n");
    sb.append("    authorization: ").append(toIndentedString(authorization)).append("\n");
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

    private ViewpointGET instance;

    public Builder() {
      this(new ViewpointGET());
    }

    protected Builder(ViewpointGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ViewpointGET value) { 
      this.instance.setIndex(value.index);
      this.instance.setGuid(value.guid);
      this.instance.setOrthogonalCamera(value.orthogonalCamera);
      this.instance.setPerspectiveCamera(value.perspectiveCamera);
      this.instance.setLines(value.lines);
      this.instance.setClippingPlanes(value.clippingPlanes);
      this.instance.setBitmaps(value.bitmaps);
      this.instance.setSnapshot(value.snapshot);
      this.instance.setAuthorization(value.authorization);
      return this;
    }

    public ViewpointGET.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public ViewpointGET.Builder index(JsonNullable<Integer> index) {
      this.instance.index = index;
      return this;
    }
    
    public ViewpointGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public ViewpointGET.Builder orthogonalCamera(OrthogonalCamera orthogonalCamera) {
      this.instance.orthogonalCamera(orthogonalCamera);
      return this;
    }
    
    public ViewpointGET.Builder orthogonalCamera(JsonNullable<OrthogonalCamera> orthogonalCamera) {
      this.instance.orthogonalCamera = orthogonalCamera;
      return this;
    }
    
    public ViewpointGET.Builder perspectiveCamera(PerspectiveCamera perspectiveCamera) {
      this.instance.perspectiveCamera(perspectiveCamera);
      return this;
    }
    
    public ViewpointGET.Builder perspectiveCamera(JsonNullable<PerspectiveCamera> perspectiveCamera) {
      this.instance.perspectiveCamera = perspectiveCamera;
      return this;
    }
    
    public ViewpointGET.Builder lines(List<Line> lines) {
      this.instance.lines(lines);
      return this;
    }
    
    public ViewpointGET.Builder lines(JsonNullable<List<Line>> lines) {
      this.instance.lines = lines;
      return this;
    }
    
    public ViewpointGET.Builder clippingPlanes(List<ClippingPlane> clippingPlanes) {
      this.instance.clippingPlanes(clippingPlanes);
      return this;
    }
    
    public ViewpointGET.Builder clippingPlanes(JsonNullable<List<ClippingPlane>> clippingPlanes) {
      this.instance.clippingPlanes = clippingPlanes;
      return this;
    }
    
    public ViewpointGET.Builder bitmaps(List<BitmapGET> bitmaps) {
      this.instance.bitmaps(bitmaps);
      return this;
    }
    
    public ViewpointGET.Builder bitmaps(JsonNullable<List<BitmapGET>> bitmaps) {
      this.instance.bitmaps = bitmaps;
      return this;
    }
    
    public ViewpointGET.Builder snapshot(SnapshotGET snapshot) {
      this.instance.snapshot(snapshot);
      return this;
    }
    
    public ViewpointGET.Builder snapshot(JsonNullable<SnapshotGET> snapshot) {
      this.instance.snapshot = snapshot;
      return this;
    }
    
    public ViewpointGET.Builder authorization(ViewpointGETAuthorization authorization) {
      this.instance.authorization(authorization);
      return this;
    }
    
    /**
    * returns a built ViewpointGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ViewpointGET build() {
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
  public static ViewpointGET.Builder builder() {
    return new ViewpointGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ViewpointGET.Builder toBuilder() {
    ViewpointGET.Builder builder = new ViewpointGET.Builder();
    return builder.copyOf(this);
  }

}

