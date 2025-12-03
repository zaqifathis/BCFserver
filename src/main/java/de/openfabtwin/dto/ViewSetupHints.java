package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ViewSetupHints
 */

@JsonTypeName("view_setup_hints")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ViewSetupHints {

  private Boolean spacesVisible = false;

  private Boolean spaceBoundariesVisible = false;

  private Boolean openingsVisible = false;

  public ViewSetupHints spacesVisible(Boolean spacesVisible) {
    this.spacesVisible = spacesVisible;
    return this;
  }

  /**
   * Get spacesVisible
   * @return spacesVisible
   */
  
  @Schema(name = "spaces_visible", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("spaces_visible")
  public Boolean getSpacesVisible() {
    return spacesVisible;
  }

  public void setSpacesVisible(Boolean spacesVisible) {
    this.spacesVisible = spacesVisible;
  }

  public ViewSetupHints spaceBoundariesVisible(Boolean spaceBoundariesVisible) {
    this.spaceBoundariesVisible = spaceBoundariesVisible;
    return this;
  }

  /**
   * Get spaceBoundariesVisible
   * @return spaceBoundariesVisible
   */
  
  @Schema(name = "space_boundaries_visible", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("space_boundaries_visible")
  public Boolean getSpaceBoundariesVisible() {
    return spaceBoundariesVisible;
  }

  public void setSpaceBoundariesVisible(Boolean spaceBoundariesVisible) {
    this.spaceBoundariesVisible = spaceBoundariesVisible;
  }

  public ViewSetupHints openingsVisible(Boolean openingsVisible) {
    this.openingsVisible = openingsVisible;
    return this;
  }

  /**
   * Get openingsVisible
   * @return openingsVisible
   */
  
  @Schema(name = "openings_visible", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("openings_visible")
  public Boolean getOpeningsVisible() {
    return openingsVisible;
  }

  public void setOpeningsVisible(Boolean openingsVisible) {
    this.openingsVisible = openingsVisible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewSetupHints viewSetupHints = (ViewSetupHints) o;
    return Objects.equals(this.spacesVisible, viewSetupHints.spacesVisible) &&
        Objects.equals(this.spaceBoundariesVisible, viewSetupHints.spaceBoundariesVisible) &&
        Objects.equals(this.openingsVisible, viewSetupHints.openingsVisible);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spacesVisible, spaceBoundariesVisible, openingsVisible);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViewSetupHints {\n");
    sb.append("    spacesVisible: ").append(toIndentedString(spacesVisible)).append("\n");
    sb.append("    spaceBoundariesVisible: ").append(toIndentedString(spaceBoundariesVisible)).append("\n");
    sb.append("    openingsVisible: ").append(toIndentedString(openingsVisible)).append("\n");
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

    private ViewSetupHints instance;

    public Builder() {
      this(new ViewSetupHints());
    }

    protected Builder(ViewSetupHints instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ViewSetupHints value) { 
      this.instance.setSpacesVisible(value.spacesVisible);
      this.instance.setSpaceBoundariesVisible(value.spaceBoundariesVisible);
      this.instance.setOpeningsVisible(value.openingsVisible);
      return this;
    }

    public ViewSetupHints.Builder spacesVisible(Boolean spacesVisible) {
      this.instance.spacesVisible(spacesVisible);
      return this;
    }
    
    public ViewSetupHints.Builder spaceBoundariesVisible(Boolean spaceBoundariesVisible) {
      this.instance.spaceBoundariesVisible(spaceBoundariesVisible);
      return this;
    }
    
    public ViewSetupHints.Builder openingsVisible(Boolean openingsVisible) {
      this.instance.openingsVisible(openingsVisible);
      return this;
    }
    
    /**
    * returns a built ViewSetupHints instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ViewSetupHints build() {
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
  public static ViewSetupHints.Builder builder() {
    return new ViewSetupHints.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ViewSetupHints.Builder toBuilder() {
    ViewSetupHints.Builder builder = new ViewSetupHints.Builder();
    return builder.copyOf(this);
  }

}

