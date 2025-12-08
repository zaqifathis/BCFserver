package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for a single document reference GET, BCF REST API.
 */

@Schema(name = "document_reference_GET", description = "Schema for a single document reference GET, BCF REST API.")
@JsonTypeName("document_reference_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentReferenceGET {

  private String guid;

  private @Nullable String documentGuid = null;

  private @Nullable String url = null;

  private @Nullable String description = null;

  public DocumentReferenceGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DocumentReferenceGET(String guid) {
    this.guid = guid;
  }

  public DocumentReferenceGET guid(String guid) {
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

  public DocumentReferenceGET documentGuid(@Nullable String documentGuid) {
    this.documentGuid = documentGuid;
    return this;
  }

  /**
   * Get documentGuid
   * @return documentGuid
   */
  
  @Schema(name = "document_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("document_guid")
  public @Nullable String getDocumentGuid() {
    return documentGuid;
  }

  public void setDocumentGuid(@Nullable String documentGuid) {
    this.documentGuid = documentGuid;
  }

  public DocumentReferenceGET url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
   */
  
  @Schema(name = "url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public DocumentReferenceGET description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentReferenceGET documentReferenceGET = (DocumentReferenceGET) o;
    return Objects.equals(this.guid, documentReferenceGET.guid) &&
        Objects.equals(this.documentGuid, documentReferenceGET.documentGuid) &&
        Objects.equals(this.url, documentReferenceGET.url) &&
        Objects.equals(this.description, documentReferenceGET.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, documentGuid, url, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentReferenceGET {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    documentGuid: ").append(toIndentedString(documentGuid)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

    private DocumentReferenceGET instance;

    public Builder() {
      this(new DocumentReferenceGET());
    }

    protected Builder(DocumentReferenceGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(DocumentReferenceGET value) { 
      this.instance.setGuid(value.guid);
      this.instance.setDocumentGuid(value.documentGuid);
      this.instance.setUrl(value.url);
      this.instance.setDescription(value.description);
      return this;
    }

    public DocumentReferenceGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public DocumentReferenceGET.Builder documentGuid(String documentGuid) {
      this.instance.documentGuid(documentGuid);
      return this;
    }
    
    public DocumentReferenceGET.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public DocumentReferenceGET.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    /**
    * returns a built DocumentReferenceGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public DocumentReferenceGET build() {
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
  public static DocumentReferenceGET.Builder builder() {
    return new DocumentReferenceGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public DocumentReferenceGET.Builder toBuilder() {
    DocumentReferenceGET.Builder builder = new DocumentReferenceGET.Builder();
    return builder.copyOf(this);
  }

}

