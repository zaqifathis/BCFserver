package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * DocumentGET
 */

@JsonTypeName("document_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentGET {

  private String guid;

  private String filename;

  public DocumentGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DocumentGET(String guid, String filename) {
    this.guid = guid;
    this.filename = filename;
  }

  public DocumentGET guid(String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  @NotNull 
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("guid")
  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public DocumentGET filename(String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Get filename
   * @return filename
   */
  @NotNull 
  @Schema(name = "filename", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("filename")
  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentGET documentGET = (DocumentGET) o;
    return Objects.equals(this.guid, documentGET.guid) &&
        Objects.equals(this.filename, documentGET.filename);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, filename);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentGET {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
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

    private DocumentGET instance;

    public Builder() {
      this(new DocumentGET());
    }

    protected Builder(DocumentGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(DocumentGET value) { 
      this.instance.setGuid(value.guid);
      this.instance.setFilename(value.filename);
      return this;
    }

    public DocumentGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public DocumentGET.Builder filename(String filename) {
      this.instance.filename(filename);
      return this;
    }
    
    /**
    * returns a built DocumentGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public DocumentGET build() {
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
  public static DocumentGET.Builder builder() {
    return new DocumentGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public DocumentGET.Builder toBuilder() {
    DocumentGET.Builder builder = new DocumentGET.Builder();
    return builder.copyOf(this);
  }

}

