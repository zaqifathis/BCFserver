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
 * Coloring
 */

@JsonTypeName("coloring")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Coloring {

  private @Nullable String color;

  @Valid
  private JsonNullable<List<@Valid Component>> components = JsonNullable.<List<@Valid Component>>undefined();

  public Coloring color(@Nullable String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
   */
  
  @Schema(name = "color", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("color")
  public @Nullable String getColor() {
    return color;
  }

  public void setColor(@Nullable String color) {
    this.color = color;
  }

  public Coloring components(List<@Valid Component> components) {
    this.components = JsonNullable.of(components);
    return this;
  }

  public Coloring addComponentsItem(Component componentsItem) {
    if (this.components == null || !this.components.isPresent()) {
      this.components = JsonNullable.of(new ArrayList<>());
    }
    this.components.get().add(componentsItem);
    return this;
  }

  /**
   * Get components
   * @return components
   */
  @Valid 
  @Schema(name = "components", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("components")
  public JsonNullable<List<@Valid Component>> getComponents() {
    return components;
  }

  public void setComponents(JsonNullable<List<@Valid Component>> components) {
    this.components = components;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coloring coloring = (Coloring) o;
    return Objects.equals(this.color, coloring.color) &&
        equalsNullable(this.components, coloring.components);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, hashCodeNullable(components));
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
    sb.append("class Coloring {\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    components: ").append(toIndentedString(components)).append("\n");
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

    private Coloring instance;

    public Builder() {
      this(new Coloring());
    }

    protected Builder(Coloring instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Coloring value) { 
      this.instance.setColor(value.color);
      this.instance.setComponents(value.components);
      return this;
    }

    public Coloring.Builder color(String color) {
      this.instance.color(color);
      return this;
    }
    
    public Coloring.Builder components(List<Component> components) {
      this.instance.components(components);
      return this;
    }
    
    public Coloring.Builder components(JsonNullable<List<Component>> components) {
      this.instance.components = components;
      return this;
    }
    
    /**
    * returns a built Coloring instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Coloring build() {
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
  public static Coloring.Builder builder() {
    return new Coloring.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Coloring.Builder toBuilder() {
    Coloring.Builder builder = new Coloring.Builder();
    return builder.copyOf(this);
  }

}

