package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * OrthogonalCamera
 */

@JsonTypeName("orthogonal_camera")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class OrthogonalCamera {

  private @Nullable Point cameraViewPoint;

  private @Nullable Direction cameraDirection;

  private @Nullable Direction cameraUpVector;

  private @Nullable BigDecimal viewToWorldScale;

  private @Nullable BigDecimal aspectRatio;

  public OrthogonalCamera cameraViewPoint(@Nullable Point cameraViewPoint) {
    this.cameraViewPoint = cameraViewPoint;
    return this;
  }

  /**
   * Get cameraViewPoint
   * @return cameraViewPoint
   */
  @Valid 
  @Schema(name = "camera_view_point", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("camera_view_point")
  public @Nullable Point getCameraViewPoint() {
    return cameraViewPoint;
  }

  public void setCameraViewPoint(@Nullable Point cameraViewPoint) {
    this.cameraViewPoint = cameraViewPoint;
  }

  public OrthogonalCamera cameraDirection(@Nullable Direction cameraDirection) {
    this.cameraDirection = cameraDirection;
    return this;
  }

  /**
   * Get cameraDirection
   * @return cameraDirection
   */
  @Valid 
  @Schema(name = "camera_direction", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("camera_direction")
  public @Nullable Direction getCameraDirection() {
    return cameraDirection;
  }

  public void setCameraDirection(@Nullable Direction cameraDirection) {
    this.cameraDirection = cameraDirection;
  }

  public OrthogonalCamera cameraUpVector(@Nullable Direction cameraUpVector) {
    this.cameraUpVector = cameraUpVector;
    return this;
  }

  /**
   * Get cameraUpVector
   * @return cameraUpVector
   */
  @Valid 
  @Schema(name = "camera_up_vector", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("camera_up_vector")
  public @Nullable Direction getCameraUpVector() {
    return cameraUpVector;
  }

  public void setCameraUpVector(@Nullable Direction cameraUpVector) {
    this.cameraUpVector = cameraUpVector;
  }

  public OrthogonalCamera viewToWorldScale(@Nullable BigDecimal viewToWorldScale) {
    this.viewToWorldScale = viewToWorldScale;
    return this;
  }

  /**
   * Get viewToWorldScale
   * @return viewToWorldScale
   */
  @Valid 
  @Schema(name = "view_to_world_scale", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("view_to_world_scale")
  public @Nullable BigDecimal getViewToWorldScale() {
    return viewToWorldScale;
  }

  public void setViewToWorldScale(@Nullable BigDecimal viewToWorldScale) {
    this.viewToWorldScale = viewToWorldScale;
  }

  public OrthogonalCamera aspectRatio(@Nullable BigDecimal aspectRatio) {
    this.aspectRatio = aspectRatio;
    return this;
  }

  /**
   * Get aspectRatio
   * @return aspectRatio
   */
  @Valid 
  @Schema(name = "aspect_ratio", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("aspect_ratio")
  public @Nullable BigDecimal getAspectRatio() {
    return aspectRatio;
  }

  public void setAspectRatio(@Nullable BigDecimal aspectRatio) {
    this.aspectRatio = aspectRatio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrthogonalCamera orthogonalCamera = (OrthogonalCamera) o;
    return Objects.equals(this.cameraViewPoint, orthogonalCamera.cameraViewPoint) &&
        Objects.equals(this.cameraDirection, orthogonalCamera.cameraDirection) &&
        Objects.equals(this.cameraUpVector, orthogonalCamera.cameraUpVector) &&
        Objects.equals(this.viewToWorldScale, orthogonalCamera.viewToWorldScale) &&
        Objects.equals(this.aspectRatio, orthogonalCamera.aspectRatio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cameraViewPoint, cameraDirection, cameraUpVector, viewToWorldScale, aspectRatio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrthogonalCamera {\n");
    sb.append("    cameraViewPoint: ").append(toIndentedString(cameraViewPoint)).append("\n");
    sb.append("    cameraDirection: ").append(toIndentedString(cameraDirection)).append("\n");
    sb.append("    cameraUpVector: ").append(toIndentedString(cameraUpVector)).append("\n");
    sb.append("    viewToWorldScale: ").append(toIndentedString(viewToWorldScale)).append("\n");
    sb.append("    aspectRatio: ").append(toIndentedString(aspectRatio)).append("\n");
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

    private OrthogonalCamera instance;

    public Builder() {
      this(new OrthogonalCamera());
    }

    protected Builder(OrthogonalCamera instance) {
      this.instance = instance;
    }

    protected Builder copyOf(OrthogonalCamera value) { 
      this.instance.setCameraViewPoint(value.cameraViewPoint);
      this.instance.setCameraDirection(value.cameraDirection);
      this.instance.setCameraUpVector(value.cameraUpVector);
      this.instance.setViewToWorldScale(value.viewToWorldScale);
      this.instance.setAspectRatio(value.aspectRatio);
      return this;
    }

    public OrthogonalCamera.Builder cameraViewPoint(Point cameraViewPoint) {
      this.instance.cameraViewPoint(cameraViewPoint);
      return this;
    }
    
    public OrthogonalCamera.Builder cameraDirection(Direction cameraDirection) {
      this.instance.cameraDirection(cameraDirection);
      return this;
    }
    
    public OrthogonalCamera.Builder cameraUpVector(Direction cameraUpVector) {
      this.instance.cameraUpVector(cameraUpVector);
      return this;
    }
    
    public OrthogonalCamera.Builder viewToWorldScale(BigDecimal viewToWorldScale) {
      this.instance.viewToWorldScale(viewToWorldScale);
      return this;
    }
    
    public OrthogonalCamera.Builder aspectRatio(BigDecimal aspectRatio) {
      this.instance.aspectRatio(aspectRatio);
      return this;
    }
    
    /**
    * returns a built OrthogonalCamera instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public OrthogonalCamera build() {
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
  public static OrthogonalCamera.Builder builder() {
    return new OrthogonalCamera.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public OrthogonalCamera.Builder toBuilder() {
    OrthogonalCamera.Builder builder = new OrthogonalCamera.Builder();
    return builder.copyOf(this);
  }

}

