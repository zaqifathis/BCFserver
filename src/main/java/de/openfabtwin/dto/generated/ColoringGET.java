package de.openfabtwin.dto.generated;

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
 * Schema for coloring GET, BCF REST API.
 */

@Schema(name = "coloring_GET", description = "Schema for coloring GET, BCF REST API.")
@JsonTypeName("coloring_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ColoringGET {

  @Valid
  private @Nullable List<@Valid Coloring> coloring;

  public ColoringGET coloring(@Nullable List<@Valid Coloring> coloring) {
    this.coloring = coloring;
    return this;
  }

  public ColoringGET addColoringItem(Coloring coloringItem) {
    if (this.coloring == null) {
      this.coloring = new ArrayList<>();
    }
    this.coloring.add(coloringItem);
    return this;
  }

  /**
   * Get coloring
   * @return coloring
   */
  @Valid 
  @Schema(name = "coloring", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coloring")
  public @Nullable List<@Valid Coloring> getColoring() {
    return coloring;
  }

  public void setColoring(@Nullable List<@Valid Coloring> coloring) {
    this.coloring = coloring;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ColoringGET coloringGET = (ColoringGET) o;
    return Objects.equals(this.coloring, coloringGET.coloring);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coloring);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ColoringGET {\n");
    sb.append("    coloring: ").append(toIndentedString(coloring)).append("\n");
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

    private ColoringGET instance;

    public Builder() {
      this(new ColoringGET());
    }

    protected Builder(ColoringGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ColoringGET value) { 
      this.instance.setColoring(value.coloring);
      return this;
    }

    public ColoringGET.Builder coloring(List<Coloring> coloring) {
      this.instance.coloring(coloring);
      return this;
    }
    
    /**
    * returns a built ColoringGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ColoringGET build() {
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
  public static ColoringGET.Builder builder() {
    return new ColoringGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ColoringGET.Builder toBuilder() {
    ColoringGET.Builder builder = new ColoringGET.Builder();
    return builder.copyOf(this);
  }

}

