package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for the display information of a project file, BCF REST API.
 */

@Schema(name = "project_file_display_information", description = "Schema for the display information of a project file, BCF REST API.")
@JsonTypeName("project_file_display_information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ProjectFileDisplayInformation {

  private String fieldDisplayName;

  private String fieldValue;

  public ProjectFileDisplayInformation() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectFileDisplayInformation(String fieldDisplayName, String fieldValue) {
    this.fieldDisplayName = fieldDisplayName;
    this.fieldValue = fieldValue;
  }

  public ProjectFileDisplayInformation fieldDisplayName(String fieldDisplayName) {
    this.fieldDisplayName = fieldDisplayName;
    return this;
  }

  /**
   * Get fieldDisplayName
   * @return fieldDisplayName
   */
  @NotNull 
  @Schema(name = "field_display_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("field_display_name")
  public String getFieldDisplayName() {
    return fieldDisplayName;
  }

  public void setFieldDisplayName(String fieldDisplayName) {
    this.fieldDisplayName = fieldDisplayName;
  }

  public ProjectFileDisplayInformation fieldValue(String fieldValue) {
    this.fieldValue = fieldValue;
    return this;
  }

  /**
   * Get fieldValue
   * @return fieldValue
   */
  @NotNull 
  @Schema(name = "field_value", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("field_value")
  public String getFieldValue() {
    return fieldValue;
  }

  public void setFieldValue(String fieldValue) {
    this.fieldValue = fieldValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectFileDisplayInformation projectFileDisplayInformation = (ProjectFileDisplayInformation) o;
    return Objects.equals(this.fieldDisplayName, projectFileDisplayInformation.fieldDisplayName) &&
        Objects.equals(this.fieldValue, projectFileDisplayInformation.fieldValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldDisplayName, fieldValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectFileDisplayInformation {\n");
    sb.append("    fieldDisplayName: ").append(toIndentedString(fieldDisplayName)).append("\n");
    sb.append("    fieldValue: ").append(toIndentedString(fieldValue)).append("\n");
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

    private ProjectFileDisplayInformation instance;

    public Builder() {
      this(new ProjectFileDisplayInformation());
    }

    protected Builder(ProjectFileDisplayInformation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProjectFileDisplayInformation value) { 
      this.instance.setFieldDisplayName(value.fieldDisplayName);
      this.instance.setFieldValue(value.fieldValue);
      return this;
    }

    public ProjectFileDisplayInformation.Builder fieldDisplayName(String fieldDisplayName) {
      this.instance.fieldDisplayName(fieldDisplayName);
      return this;
    }
    
    public ProjectFileDisplayInformation.Builder fieldValue(String fieldValue) {
      this.instance.fieldValue(fieldValue);
      return this;
    }
    
    /**
    * returns a built ProjectFileDisplayInformation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProjectFileDisplayInformation build() {
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
  public static ProjectFileDisplayInformation.Builder builder() {
    return new ProjectFileDisplayInformation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProjectFileDisplayInformation.Builder toBuilder() {
    ProjectFileDisplayInformation.Builder builder = new ProjectFileDisplayInformation.Builder();
    return builder.copyOf(this);
  }

}

