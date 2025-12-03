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
 * Visibility
 */

@JsonTypeName("visibility")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Visibility {

  private Boolean defaultVisibility = false;

  @Valid
  private JsonNullable<List<@Valid Component>> exceptions = JsonNullable.<List<@Valid Component>>undefined();

  private JsonNullable<ViewSetupHints> viewSetupHints = JsonNullable.<ViewSetupHints>undefined();

  public Visibility defaultVisibility(Boolean defaultVisibility) {
    this.defaultVisibility = defaultVisibility;
    return this;
  }

  /**
   * Get defaultVisibility
   * @return defaultVisibility
   */
  
  @Schema(name = "default_visibility", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("default_visibility")
  public Boolean getDefaultVisibility() {
    return defaultVisibility;
  }

  public void setDefaultVisibility(Boolean defaultVisibility) {
    this.defaultVisibility = defaultVisibility;
  }

  public Visibility exceptions(List<@Valid Component> exceptions) {
    this.exceptions = JsonNullable.of(exceptions);
    return this;
  }

  public Visibility addExceptionsItem(Component exceptionsItem) {
    if (this.exceptions == null || !this.exceptions.isPresent()) {
      this.exceptions = JsonNullable.of(new ArrayList<>());
    }
    this.exceptions.get().add(exceptionsItem);
    return this;
  }

  /**
   * Get exceptions
   * @return exceptions
   */
  @Valid 
  @Schema(name = "exceptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exceptions")
  public JsonNullable<List<@Valid Component>> getExceptions() {
    return exceptions;
  }

  public void setExceptions(JsonNullable<List<@Valid Component>> exceptions) {
    this.exceptions = exceptions;
  }

  public Visibility viewSetupHints(ViewSetupHints viewSetupHints) {
    this.viewSetupHints = JsonNullable.of(viewSetupHints);
    return this;
  }

  /**
   * Get viewSetupHints
   * @return viewSetupHints
   */
  @Valid 
  @Schema(name = "view_setup_hints", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("view_setup_hints")
  public JsonNullable<ViewSetupHints> getViewSetupHints() {
    return viewSetupHints;
  }

  public void setViewSetupHints(JsonNullable<ViewSetupHints> viewSetupHints) {
    this.viewSetupHints = viewSetupHints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Visibility visibility = (Visibility) o;
    return Objects.equals(this.defaultVisibility, visibility.defaultVisibility) &&
        equalsNullable(this.exceptions, visibility.exceptions) &&
        equalsNullable(this.viewSetupHints, visibility.viewSetupHints);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultVisibility, hashCodeNullable(exceptions), hashCodeNullable(viewSetupHints));
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
    sb.append("class Visibility {\n");
    sb.append("    defaultVisibility: ").append(toIndentedString(defaultVisibility)).append("\n");
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
    sb.append("    viewSetupHints: ").append(toIndentedString(viewSetupHints)).append("\n");
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

    private Visibility instance;

    public Builder() {
      this(new Visibility());
    }

    protected Builder(Visibility instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Visibility value) { 
      this.instance.setDefaultVisibility(value.defaultVisibility);
      this.instance.setExceptions(value.exceptions);
      this.instance.setViewSetupHints(value.viewSetupHints);
      return this;
    }

    public Visibility.Builder defaultVisibility(Boolean defaultVisibility) {
      this.instance.defaultVisibility(defaultVisibility);
      return this;
    }
    
    public Visibility.Builder exceptions(List<Component> exceptions) {
      this.instance.exceptions(exceptions);
      return this;
    }
    
    public Visibility.Builder exceptions(JsonNullable<List<Component>> exceptions) {
      this.instance.exceptions = exceptions;
      return this;
    }
    
    public Visibility.Builder viewSetupHints(ViewSetupHints viewSetupHints) {
      this.instance.viewSetupHints(viewSetupHints);
      return this;
    }
    
    public Visibility.Builder viewSetupHints(JsonNullable<ViewSetupHints> viewSetupHints) {
      this.instance.viewSetupHints = viewSetupHints;
      return this;
    }
    
    /**
    * returns a built Visibility instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Visibility build() {
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
  public static Visibility.Builder builder() {
    return new Visibility.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Visibility.Builder toBuilder() {
    Visibility.Builder builder = new Visibility.Builder();
    return builder.copyOf(this);
  }

}

