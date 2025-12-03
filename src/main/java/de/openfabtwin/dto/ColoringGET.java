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
 * Schema for coloring GET, BCF REST API.
 */

@Schema(name = "coloring_GET", description = "Schema for coloring GET, BCF REST API.")
@JsonTypeName("coloring_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ColoringGET {

  @Valid
  private JsonNullable<List<@Valid Coloring>> coloring = JsonNullable.<List<@Valid Coloring>>undefined();

  public ColoringGET coloring(List<@Valid Coloring> coloring) {
    this.coloring = JsonNullable.of(coloring);
    return this;
  }

  public ColoringGET addColoringItem(Coloring coloringItem) {
    if (this.coloring == null || !this.coloring.isPresent()) {
      this.coloring = JsonNullable.of(new ArrayList<>());
    }
    this.coloring.get().add(coloringItem);
    return this;
  }

  /**
   * Get coloring
   * @return coloring
   */
  @Valid 
  @Schema(name = "coloring", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coloring")
  public JsonNullable<List<@Valid Coloring>> getColoring() {
    return coloring;
  }

  public void setColoring(JsonNullable<List<@Valid Coloring>> coloring) {
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
    return equalsNullable(this.coloring, coloringGET.coloring);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(coloring));
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
    
    public ColoringGET.Builder coloring(JsonNullable<List<Coloring>> coloring) {
      this.instance.coloring = coloring;
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

