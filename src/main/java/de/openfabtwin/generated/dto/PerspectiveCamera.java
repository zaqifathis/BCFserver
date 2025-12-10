package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * PerspectiveCamera
 */

@JsonTypeName("perspective_camera")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class PerspectiveCamera {

  private @Nullable Point cameraViewPoint;

  private @Nullable Direction cameraDirection;

  private @Nullable Direction cameraUpVector;

  private @Nullable BigDecimal fieldOfView;

  private @Nullable BigDecimal aspectRatio;

  public PerspectiveCamera cameraViewPoint(@Nullable Point cameraViewPoint) {
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

  public PerspectiveCamera cameraDirection(@Nullable Direction cameraDirection) {
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

  public PerspectiveCamera cameraUpVector(@Nullable Direction cameraUpVector) {
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

  public PerspectiveCamera fieldOfView(@Nullable BigDecimal fieldOfView) {
    this.fieldOfView = fieldOfView;
    return this;
  }

  /**
   * Get fieldOfView
   * @return fieldOfView
   */
  @Valid 
  @Schema(name = "field_of_view", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("field_of_view")
  public @Nullable BigDecimal getFieldOfView() {
    return fieldOfView;
  }

  public void setFieldOfView(@Nullable BigDecimal fieldOfView) {
    this.fieldOfView = fieldOfView;
  }

  public PerspectiveCamera aspectRatio(@Nullable BigDecimal aspectRatio) {
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
    PerspectiveCamera perspectiveCamera = (PerspectiveCamera) o;
    return Objects.equals(this.cameraViewPoint, perspectiveCamera.cameraViewPoint) &&
        Objects.equals(this.cameraDirection, perspectiveCamera.cameraDirection) &&
        Objects.equals(this.cameraUpVector, perspectiveCamera.cameraUpVector) &&
        Objects.equals(this.fieldOfView, perspectiveCamera.fieldOfView) &&
        Objects.equals(this.aspectRatio, perspectiveCamera.aspectRatio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cameraViewPoint, cameraDirection, cameraUpVector, fieldOfView, aspectRatio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerspectiveCamera {\n");
    sb.append("    cameraViewPoint: ").append(toIndentedString(cameraViewPoint)).append("\n");
    sb.append("    cameraDirection: ").append(toIndentedString(cameraDirection)).append("\n");
    sb.append("    cameraUpVector: ").append(toIndentedString(cameraUpVector)).append("\n");
    sb.append("    fieldOfView: ").append(toIndentedString(fieldOfView)).append("\n");
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

    private PerspectiveCamera instance;

    public Builder() {
      this(new PerspectiveCamera());
    }

    protected Builder(PerspectiveCamera instance) {
      this.instance = instance;
    }

    protected Builder copyOf(PerspectiveCamera value) { 
      this.instance.setCameraViewPoint(value.cameraViewPoint);
      this.instance.setCameraDirection(value.cameraDirection);
      this.instance.setCameraUpVector(value.cameraUpVector);
      this.instance.setFieldOfView(value.fieldOfView);
      this.instance.setAspectRatio(value.aspectRatio);
      return this;
    }

    public PerspectiveCamera.Builder cameraViewPoint(Point cameraViewPoint) {
      this.instance.cameraViewPoint(cameraViewPoint);
      return this;
    }
    
    public PerspectiveCamera.Builder cameraDirection(Direction cameraDirection) {
      this.instance.cameraDirection(cameraDirection);
      return this;
    }
    
    public PerspectiveCamera.Builder cameraUpVector(Direction cameraUpVector) {
      this.instance.cameraUpVector(cameraUpVector);
      return this;
    }
    
    public PerspectiveCamera.Builder fieldOfView(BigDecimal fieldOfView) {
      this.instance.fieldOfView(fieldOfView);
      return this;
    }
    
    public PerspectiveCamera.Builder aspectRatio(BigDecimal aspectRatio) {
      this.instance.aspectRatio(aspectRatio);
      return this;
    }
    
    /**
    * returns a built PerspectiveCamera instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public PerspectiveCamera build() {
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
  public static PerspectiveCamera.Builder builder() {
    return new PerspectiveCamera.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public PerspectiveCamera.Builder toBuilder() {
    PerspectiveCamera.Builder builder = new PerspectiveCamera.Builder();
    return builder.copyOf(this);
  }

}

