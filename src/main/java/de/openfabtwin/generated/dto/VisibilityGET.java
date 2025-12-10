package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * VisibilityGET
 */

@JsonTypeName("visibility_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class VisibilityGET {

  private @Nullable Visibility visibility;

  public VisibilityGET visibility(@Nullable Visibility visibility) {
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
    VisibilityGET visibilityGET = (VisibilityGET) o;
    return Objects.equals(this.visibility, visibilityGET.visibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisibilityGET {\n");
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

    private VisibilityGET instance;

    public Builder() {
      this(new VisibilityGET());
    }

    protected Builder(VisibilityGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(VisibilityGET value) { 
      this.instance.setVisibility(value.visibility);
      return this;
    }

    public VisibilityGET.Builder visibility(Visibility visibility) {
      this.instance.visibility(visibility);
      return this;
    }
    
    /**
    * returns a built VisibilityGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public VisibilityGET build() {
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
  public static VisibilityGET.Builder builder() {
    return new VisibilityGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public VisibilityGET.Builder toBuilder() {
    VisibilityGET.Builder builder = new VisibilityGET.Builder();
    return builder.copyOf(this);
  }

}

