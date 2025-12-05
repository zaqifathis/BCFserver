package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ClippingPlane
 */

@JsonTypeName("clipping_plane")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ClippingPlane {

  private @Nullable Location location = null;

  private @Nullable Direction direction;

  public ClippingPlane location(@Nullable Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @Valid 
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public @Nullable Location getLocation() {
    return location;
  }

  public void setLocation(@Nullable Location location) {
    this.location = location;
  }

  public ClippingPlane direction(@Nullable Direction direction) {
    this.direction = direction;
    return this;
  }

  /**
   * Get direction
   * @return direction
   */
  @Valid 
  @Schema(name = "direction", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("direction")
  public @Nullable Direction getDirection() {
    return direction;
  }

  public void setDirection(@Nullable Direction direction) {
    this.direction = direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClippingPlane clippingPlane = (ClippingPlane) o;
    return Objects.equals(this.location, clippingPlane.location) &&
        Objects.equals(this.direction, clippingPlane.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, direction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClippingPlane {\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
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

    private ClippingPlane instance;

    public Builder() {
      this(new ClippingPlane());
    }

    protected Builder(ClippingPlane instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ClippingPlane value) { 
      this.instance.setLocation(value.location);
      this.instance.setDirection(value.direction);
      return this;
    }

    public ClippingPlane.Builder location(Location location) {
      this.instance.location(location);
      return this;
    }
    
    public ClippingPlane.Builder direction(Direction direction) {
      this.instance.direction(direction);
      return this;
    }
    
    /**
    * returns a built ClippingPlane instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ClippingPlane build() {
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
  public static ClippingPlane.Builder builder() {
    return new ClippingPlane.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ClippingPlane.Builder toBuilder() {
    ClippingPlane.Builder builder = new ClippingPlane.Builder();
    return builder.copyOf(this);
  }

}

