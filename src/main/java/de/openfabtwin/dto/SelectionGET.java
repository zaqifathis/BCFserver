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
 * Schema for selection GET, BCF REST API.
 */

@Schema(name = "selection_GET", description = "Schema for selection GET, BCF REST API.")
@JsonTypeName("selection_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class SelectionGET {

  @Valid
  private JsonNullable<List<@Valid Component>> selection = JsonNullable.<List<@Valid Component>>undefined();

  public SelectionGET selection(List<@Valid Component> selection) {
    this.selection = JsonNullable.of(selection);
    return this;
  }

  public SelectionGET addSelectionItem(Component selectionItem) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SelectionGET selectionGET = (SelectionGET) o;
    return equalsNullable(this.selection, selectionGET.selection);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(selection));
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
    sb.append("class SelectionGET {\n");
    sb.append("    selection: ").append(toIndentedString(selection)).append("\n");
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

    private SelectionGET instance;

    public Builder() {
      this(new SelectionGET());
    }

    protected Builder(SelectionGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(SelectionGET value) { 
      this.instance.setSelection(value.selection);
      return this;
    }

    public SelectionGET.Builder selection(List<Component> selection) {
      this.instance.selection(selection);
      return this;
    }
    
    public SelectionGET.Builder selection(JsonNullable<List<Component>> selection) {
      this.instance.selection = selection;
      return this;
    }
    
    /**
    * returns a built SelectionGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public SelectionGET build() {
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
  public static SelectionGET.Builder builder() {
    return new SelectionGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public SelectionGET.Builder toBuilder() {
    SelectionGET.Builder builder = new SelectionGET.Builder();
    return builder.copyOf(this);
  }

}

