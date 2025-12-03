package de.openfabtwin.dto;

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
 * Schema for a single project file information, BCF REST API.
 */

@Schema(name = "project_file_information", description = "Schema for a single project file information, BCF REST API.")
@JsonTypeName("project_file_information")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ProjectFileInformation {

  @Valid
  private List<@Valid ProjectFileDisplayInformation> displayInformation = new ArrayList<>();

  private @Nullable FileGET file;

  public ProjectFileInformation displayInformation(List<@Valid ProjectFileDisplayInformation> displayInformation) {
    this.displayInformation = displayInformation;
    return this;
  }

  public ProjectFileInformation addDisplayInformationItem(ProjectFileDisplayInformation displayInformationItem) {
    if (this.displayInformation == null) {
      this.displayInformation = new ArrayList<>();
    }
    this.displayInformation.add(displayInformationItem);
    return this;
  }

  /**
   * Get displayInformation
   * @return displayInformation
   */
  @Valid 
  @Schema(name = "display_information", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("display_information")
  public List<@Valid ProjectFileDisplayInformation> getDisplayInformation() {
    return displayInformation;
  }

  public void setDisplayInformation(List<@Valid ProjectFileDisplayInformation> displayInformation) {
    this.displayInformation = displayInformation;
  }

  public ProjectFileInformation file(@Nullable FileGET file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
   */
  @Valid 
  @Schema(name = "file", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("file")
  public @Nullable FileGET getFile() {
    return file;
  }

  public void setFile(@Nullable FileGET file) {
    this.file = file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectFileInformation projectFileInformation = (ProjectFileInformation) o;
    return Objects.equals(this.displayInformation, projectFileInformation.displayInformation) &&
        Objects.equals(this.file, projectFileInformation.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayInformation, file);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectFileInformation {\n");
    sb.append("    displayInformation: ").append(toIndentedString(displayInformation)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
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

    private ProjectFileInformation instance;

    public Builder() {
      this(new ProjectFileInformation());
    }

    protected Builder(ProjectFileInformation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProjectFileInformation value) { 
      this.instance.setDisplayInformation(value.displayInformation);
      this.instance.setFile(value.file);
      return this;
    }

    public ProjectFileInformation.Builder displayInformation(List<ProjectFileDisplayInformation> displayInformation) {
      this.instance.displayInformation(displayInformation);
      return this;
    }
    
    public ProjectFileInformation.Builder file(FileGET file) {
      this.instance.file(file);
      return this;
    }
    
    /**
    * returns a built ProjectFileInformation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProjectFileInformation build() {
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
  public static ProjectFileInformation.Builder builder() {
    return new ProjectFileInformation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProjectFileInformation.Builder toBuilder() {
    ProjectFileInformation.Builder builder = new ProjectFileInformation.Builder();
    return builder.copyOf(this);
  }

}

