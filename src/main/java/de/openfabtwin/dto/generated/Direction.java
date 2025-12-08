package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Direction
 */

@JsonTypeName("direction")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Direction {

  private @Nullable BigDecimal x;

  private @Nullable BigDecimal y;

  private @Nullable BigDecimal z;

  public Direction x(@Nullable BigDecimal x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * @return x
   */
  @Valid 
  @Schema(name = "x", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("x")
  public @Nullable BigDecimal getX() {
    return x;
  }

  public void setX(@Nullable BigDecimal x) {
    this.x = x;
  }

  public Direction y(@Nullable BigDecimal y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * @return y
   */
  @Valid 
  @Schema(name = "y", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("y")
  public @Nullable BigDecimal getY() {
    return y;
  }

  public void setY(@Nullable BigDecimal y) {
    this.y = y;
  }

  public Direction z(@Nullable BigDecimal z) {
    this.z = z;
    return this;
  }

  /**
   * Get z
   * @return z
   */
  @Valid 
  @Schema(name = "z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("z")
  public @Nullable BigDecimal getZ() {
    return z;
  }

  public void setZ(@Nullable BigDecimal z) {
    this.z = z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Direction direction = (Direction) o;
    return Objects.equals(this.x, direction.x) &&
        Objects.equals(this.y, direction.y) &&
        Objects.equals(this.z, direction.z);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Direction {\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
    sb.append("    z: ").append(toIndentedString(z)).append("\n");
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

    private Direction instance;

    public Builder() {
      this(new Direction());
    }

    protected Builder(Direction instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Direction value) { 
      this.instance.setX(value.x);
      this.instance.setY(value.y);
      this.instance.setZ(value.z);
      return this;
    }

    public Direction.Builder x(BigDecimal x) {
      this.instance.x(x);
      return this;
    }
    
    public Direction.Builder y(BigDecimal y) {
      this.instance.y(y);
      return this;
    }
    
    public Direction.Builder z(BigDecimal z) {
      this.instance.z(z);
      return this;
    }
    
    /**
    * returns a built Direction instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Direction build() {
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
  public static Direction.Builder builder() {
    return new Direction.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Direction.Builder toBuilder() {
    Direction.Builder builder = new Direction.Builder();
    return builder.copyOf(this);
  }

}

