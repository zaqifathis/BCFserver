package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Line
 */

@JsonTypeName("line")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Line {

  private @Nullable Point startPoint;

  private @Nullable Point endPoint;

  public Line startPoint(@Nullable Point startPoint) {
    this.startPoint = startPoint;
    return this;
  }

  /**
   * Get startPoint
   * @return startPoint
   */
  @Valid 
  @Schema(name = "start_point", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("start_point")
  public @Nullable Point getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(@Nullable Point startPoint) {
    this.startPoint = startPoint;
  }

  public Line endPoint(@Nullable Point endPoint) {
    this.endPoint = endPoint;
    return this;
  }

  /**
   * Get endPoint
   * @return endPoint
   */
  @Valid 
  @Schema(name = "end_point", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end_point")
  public @Nullable Point getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(@Nullable Point endPoint) {
    this.endPoint = endPoint;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Line line = (Line) o;
    return Objects.equals(this.startPoint, line.startPoint) &&
        Objects.equals(this.endPoint, line.endPoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startPoint, endPoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Line {\n");
    sb.append("    startPoint: ").append(toIndentedString(startPoint)).append("\n");
    sb.append("    endPoint: ").append(toIndentedString(endPoint)).append("\n");
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

    private Line instance;

    public Builder() {
      this(new Line());
    }

    protected Builder(Line instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Line value) { 
      this.instance.setStartPoint(value.startPoint);
      this.instance.setEndPoint(value.endPoint);
      return this;
    }

    public Line.Builder startPoint(Point startPoint) {
      this.instance.startPoint(startPoint);
      return this;
    }
    
    public Line.Builder endPoint(Point endPoint) {
      this.instance.endPoint(endPoint);
      return this;
    }
    
    /**
    * returns a built Line instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Line build() {
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
  public static Line.Builder builder() {
    return new Line.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Line.Builder toBuilder() {
    Line.Builder builder = new Line.Builder();
    return builder.copyOf(this);
  }

}

