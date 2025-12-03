package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * FilePUT
 */

@JsonTypeName("file_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class FilePUT {

  private JsonNullable<String> ifcProject = JsonNullable.<String>undefined();

  private JsonNullable<String> ifcSpatialStructureElement = JsonNullable.<String>undefined();

  private JsonNullable<String> filename = JsonNullable.<String>undefined();

  private JsonNullable<String> date = JsonNullable.<String>undefined();

  private JsonNullable<String> reference = JsonNullable.<String>undefined();

  public FilePUT ifcProject(String ifcProject) {
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

  public FilePUT ifcSpatialStructureElement(String ifcSpatialStructureElement) {
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

  public FilePUT filename(String filename) {
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

  public FilePUT date(String date) {
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

  public FilePUT reference(String reference) {
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
    FilePUT filePUT = (FilePUT) o;
    return equalsNullable(this.ifcProject, filePUT.ifcProject) &&
        equalsNullable(this.ifcSpatialStructureElement, filePUT.ifcSpatialStructureElement) &&
        equalsNullable(this.filename, filePUT.filename) &&
        equalsNullable(this.date, filePUT.date) &&
        equalsNullable(this.reference, filePUT.reference);
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
    sb.append("class FilePUT {\n");
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

    private FilePUT instance;

    public Builder() {
      this(new FilePUT());
    }

    protected Builder(FilePUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FilePUT value) { 
      this.instance.setIfcProject(value.ifcProject);
      this.instance.setIfcSpatialStructureElement(value.ifcSpatialStructureElement);
      this.instance.setFilename(value.filename);
      this.instance.setDate(value.date);
      this.instance.setReference(value.reference);
      return this;
    }

    public FilePUT.Builder ifcProject(String ifcProject) {
      this.instance.ifcProject(ifcProject);
      return this;
    }
    
    public FilePUT.Builder ifcProject(JsonNullable<String> ifcProject) {
      this.instance.ifcProject = ifcProject;
      return this;
    }
    
    public FilePUT.Builder ifcSpatialStructureElement(String ifcSpatialStructureElement) {
      this.instance.ifcSpatialStructureElement(ifcSpatialStructureElement);
      return this;
    }
    
    public FilePUT.Builder ifcSpatialStructureElement(JsonNullable<String> ifcSpatialStructureElement) {
      this.instance.ifcSpatialStructureElement = ifcSpatialStructureElement;
      return this;
    }
    
    public FilePUT.Builder filename(String filename) {
      this.instance.filename(filename);
      return this;
    }
    
    public FilePUT.Builder filename(JsonNullable<String> filename) {
      this.instance.filename = filename;
      return this;
    }
    
    public FilePUT.Builder date(String date) {
      this.instance.date(date);
      return this;
    }
    
    public FilePUT.Builder date(JsonNullable<String> date) {
      this.instance.date = date;
      return this;
    }
    
    public FilePUT.Builder reference(String reference) {
      this.instance.reference(reference);
      return this;
    }
    
    public FilePUT.Builder reference(JsonNullable<String> reference) {
      this.instance.reference = reference;
      return this;
    }
    
    /**
    * returns a built FilePUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FilePUT build() {
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
  public static FilePUT.Builder builder() {
    return new FilePUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FilePUT.Builder toBuilder() {
    FilePUT.Builder builder = new FilePUT.Builder();
    return builder.copyOf(this);
  }

}

