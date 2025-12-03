package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for a single file GET, BCF REST API.
 */

@Schema(name = "file_GET", description = "Schema for a single file GET, BCF REST API.")
@JsonTypeName("file_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class FileGET {

  private JsonNullable<String> ifcProject = JsonNullable.<String>undefined();

  private JsonNullable<String> ifcSpatialStructureElement = JsonNullable.<String>undefined();

  private JsonNullable<String> filename = JsonNullable.<String>undefined();

  private JsonNullable<String> date = JsonNullable.<String>undefined();

  private JsonNullable<String> reference = JsonNullable.<String>undefined();

  public FileGET ifcProject(String ifcProject) {
    this.ifcProject = JsonNullable.of(ifcProject);
    return this;
  }

  /**
   * Get ifcProject
   * @return ifcProject
   */
  
  @Schema(name = "ifc_project", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ifc_project")
  public JsonNullable<String> getIfcProject() {
    return ifcProject;
  }

  public void setIfcProject(JsonNullable<String> ifcProject) {
    this.ifcProject = ifcProject;
  }

  public FileGET ifcSpatialStructureElement(String ifcSpatialStructureElement) {
    this.ifcSpatialStructureElement = JsonNullable.of(ifcSpatialStructureElement);
    return this;
  }

  /**
   * Get ifcSpatialStructureElement
   * @return ifcSpatialStructureElement
   */
  
  @Schema(name = "ifc_spatial_structure_element", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ifc_spatial_structure_element")
  public JsonNullable<String> getIfcSpatialStructureElement() {
    return ifcSpatialStructureElement;
  }

  public void setIfcSpatialStructureElement(JsonNullable<String> ifcSpatialStructureElement) {
    this.ifcSpatialStructureElement = ifcSpatialStructureElement;
  }

  public FileGET filename(String filename) {
    this.filename = JsonNullable.of(filename);
    return this;
  }

  /**
   * Get filename
   * @return filename
   */
  
  @Schema(name = "filename", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filename")
  public JsonNullable<String> getFilename() {
    return filename;
  }

  public void setFilename(JsonNullable<String> filename) {
    this.filename = filename;
  }

  public FileGET date(String date) {
    this.date = JsonNullable.of(date);
    return this;
  }

  /**
   * Get date
   * @return date
   */
  
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public JsonNullable<String> getDate() {
    return date;
  }

  public void setDate(JsonNullable<String> date) {
    this.date = date;
  }

  public FileGET reference(String reference) {
    this.reference = JsonNullable.of(reference);
    return this;
  }

  /**
   * Get reference
   * @return reference
   */
  
  @Schema(name = "reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reference")
  public JsonNullable<String> getReference() {
    return reference;
  }

  public void setReference(JsonNullable<String> reference) {
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
    return equalsNullable(this.ifcProject, fileGET.ifcProject) &&
        equalsNullable(this.ifcSpatialStructureElement, fileGET.ifcSpatialStructureElement) &&
        equalsNullable(this.filename, fileGET.filename) &&
        equalsNullable(this.date, fileGET.date) &&
        equalsNullable(this.reference, fileGET.reference);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(ifcProject), hashCodeNullable(ifcSpatialStructureElement), hashCodeNullable(filename), hashCodeNullable(date), hashCodeNullable(reference));
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
    
    public FileGET.Builder ifcProject(JsonNullable<String> ifcProject) {
      this.instance.ifcProject = ifcProject;
      return this;
    }
    
    public FileGET.Builder ifcSpatialStructureElement(String ifcSpatialStructureElement) {
      this.instance.ifcSpatialStructureElement(ifcSpatialStructureElement);
      return this;
    }
    
    public FileGET.Builder ifcSpatialStructureElement(JsonNullable<String> ifcSpatialStructureElement) {
      this.instance.ifcSpatialStructureElement = ifcSpatialStructureElement;
      return this;
    }
    
    public FileGET.Builder filename(String filename) {
      this.instance.filename(filename);
      return this;
    }
    
    public FileGET.Builder filename(JsonNullable<String> filename) {
      this.instance.filename = filename;
      return this;
    }
    
    public FileGET.Builder date(String date) {
      this.instance.date(date);
      return this;
    }
    
    public FileGET.Builder date(JsonNullable<String> date) {
      this.instance.date = date;
      return this;
    }
    
    public FileGET.Builder reference(String reference) {
      this.instance.reference(reference);
      return this;
    }
    
    public FileGET.Builder reference(JsonNullable<String> reference) {
      this.instance.reference = reference;
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

