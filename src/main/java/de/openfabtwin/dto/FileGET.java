package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for a single file GET, BCF REST API.
 */

@Schema(name = "file_GET", description = "Schema for a single file GET, BCF REST API.")
@JsonTypeName("file_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class FileGET {

  private @Nullable String ifcProject = null;

  private @Nullable String ifcSpatialStructureElement = null;

  private @Nullable String filename = null;

  private @Nullable String date = null;

  private @Nullable String reference = null;

  public FileGET ifcProject(@Nullable String ifcProject) {
    this.ifcProject = ifcProject;
    return this;
  }

  /**
   * Get ifcProject
   * @return ifcProject
   */
  
  @Schema(name = "ifc_project", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ifc_project")
  public @Nullable String getIfcProject() {
    return ifcProject;
  }

  public void setIfcProject(@Nullable String ifcProject) {
    this.ifcProject = ifcProject;
  }

  public FileGET ifcSpatialStructureElement(@Nullable String ifcSpatialStructureElement) {
    this.ifcSpatialStructureElement = ifcSpatialStructureElement;
    return this;
  }

  /**
   * Get ifcSpatialStructureElement
   * @return ifcSpatialStructureElement
   */
  
  @Schema(name = "ifc_spatial_structure_element", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ifc_spatial_structure_element")
  public @Nullable String getIfcSpatialStructureElement() {
    return ifcSpatialStructureElement;
  }

  public void setIfcSpatialStructureElement(@Nullable String ifcSpatialStructureElement) {
    this.ifcSpatialStructureElement = ifcSpatialStructureElement;
  }

  public FileGET filename(@Nullable String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Get filename
   * @return filename
   */
  
  @Schema(name = "filename", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filename")
  public @Nullable String getFilename() {
    return filename;
  }

  public void setFilename(@Nullable String filename) {
    this.filename = filename;
  }

  public FileGET date(@Nullable String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public @Nullable String getDate() {
    return date;
  }

  public void setDate(@Nullable String date) {
    this.date = date;
  }

  public FileGET reference(@Nullable String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
   */
  
  @Schema(name = "reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reference")
  public @Nullable String getReference() {
    return reference;
  }

  public void setReference(@Nullable String reference) {
    this.reference = reference;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileGET fileGET = (FileGET) o;
    return Objects.equals(this.ifcProject, fileGET.ifcProject) &&
        Objects.equals(this.ifcSpatialStructureElement, fileGET.ifcSpatialStructureElement) &&
        Objects.equals(this.filename, fileGET.filename) &&
        Objects.equals(this.date, fileGET.date) &&
        Objects.equals(this.reference, fileGET.reference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ifcProject, ifcSpatialStructureElement, filename, date, reference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileGET {\n");
    sb.append("    ifcProject: ").append(toIndentedString(ifcProject)).append("\n");
    sb.append("    ifcSpatialStructureElement: ").append(toIndentedString(ifcSpatialStructureElement)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
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

    private FileGET instance;

    public Builder() {
      this(new FileGET());
    }

    protected Builder(FileGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FileGET value) { 
      this.instance.setIfcProject(value.ifcProject);
      this.instance.setIfcSpatialStructureElement(value.ifcSpatialStructureElement);
      this.instance.setFilename(value.filename);
      this.instance.setDate(value.date);
      this.instance.setReference(value.reference);
      return this;
    }

    public FileGET.Builder ifcProject(String ifcProject) {
      this.instance.ifcProject(ifcProject);
      return this;
    }
    
    public FileGET.Builder ifcSpatialStructureElement(String ifcSpatialStructureElement) {
      this.instance.ifcSpatialStructureElement(ifcSpatialStructureElement);
      return this;
    }
    
    public FileGET.Builder filename(String filename) {
      this.instance.filename(filename);
      return this;
    }
    
    public FileGET.Builder date(String date) {
      this.instance.date(date);
      return this;
    }
    
    public FileGET.Builder reference(String reference) {
      this.instance.reference(reference);
      return this;
    }
    
    /**
    * returns a built FileGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FileGET build() {
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
  public static FileGET.Builder builder() {
    return new FileGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FileGET.Builder toBuilder() {
    FileGET.Builder builder = new FileGET.Builder();
    return builder.copyOf(this);
  }

}

