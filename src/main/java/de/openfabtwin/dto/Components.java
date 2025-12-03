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
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Components
 */

@JsonTypeName("components")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Components {

  @Valid
  private JsonNullable<List<@Valid Component>> selection = JsonNullable.<List<@Valid Component>>undefined();

  @Valid
  private JsonNullable<List<@Valid Coloring>> coloring = JsonNullable.<List<@Valid Coloring>>undefined();

  private @Nullable Visibility visibility;

  public Components selection(List<@Valid Component> selection) {
    this.selection = JsonNullable.of(selection);
    return this;
  }

  public Components addSelectionItem(Component selectionItem) {
    if (this.selection == null || !this.selection.isPresent()) {
      this.selection = JsonNullable.of(new ArrayList<>());
    }
    this.selection.get().add(selectionItem);
    return this;
  }

  /**
   * Get selection
   * @return selection
   */
  @Valid 
  @Schema(name = "selection", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("selection")
  public JsonNullable<List<@Valid Component>> getSelection() {
    return selection;
  }

  public void setSelection(JsonNullable<List<@Valid Component>> selection) {
    this.selection = selection;
  }

  public Components coloring(List<@Valid Coloring> coloring) {
    this.coloring = JsonNullable.of(coloring);
    return this;
  }

  public Components addColoringItem(Coloring coloringItem) {
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

  public Components visibility(@Nullable Visibility visibility) {
    this.visibility = visibility;
    return this;
  }

  /**
   * Get visibility
   * @return visibility
   */
  @Valid 
  @Schema(name = "visibility", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("visibility")
  public @Nullable Visibility getVisibility() {
    return visibility;
  }

  public void setVisibility(@Nullable Visibility visibility) {
    this.visibility = visibility;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Components components = (Components) o;
    return equalsNullable(this.selection, components.selection) &&
        equalsNullable(this.coloring, components.coloring) &&
        Objects.equals(this.visibility, components.visibility);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(selection), hashCodeNullable(coloring), visibility);
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
    sb.append("class Components {\n");
    sb.append("    selection: ").append(toIndentedString(selection)).append("\n");
    sb.append("    coloring: ").append(toIndentedString(coloring)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
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

    private Components instance;

    public Builder() {
      this(new Components());
    }

    protected Builder(Components instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Components value) { 
      this.instance.setSelection(value.selection);
      this.instance.setColoring(value.coloring);
      this.instance.setVisibility(value.visibility);
      return this;
    }

    public Components.Builder selection(List<Component> selection) {
      this.instance.selection(selection);
      return this;
    }
    
    public Components.Builder selection(JsonNullable<List<Component>> selection) {
      this.instance.selection = selection;
      return this;
    }
    
    public Components.Builder coloring(List<Coloring> coloring) {
      this.instance.coloring(coloring);
      return this;
    }
    
    public Components.Builder coloring(JsonNullable<List<Coloring>> coloring) {
      this.instance.coloring = coloring;
      return this;
    }
    
    public Components.Builder visibility(Visibility visibility) {
      this.instance.visibility(visibility);
      return this;
    }
    
    /**
    * returns a built Components instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Components build() {
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
  public static Components.Builder builder() {
    return new Components.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Components.Builder toBuilder() {
    Components.Builder builder = new Components.Builder();
    return builder.copyOf(this);
  }

}

