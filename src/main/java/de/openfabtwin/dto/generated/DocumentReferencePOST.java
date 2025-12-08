package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * DocumentReferencePOST
 */

@JsonTypeName("document_reference_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentReferencePOST {

  private @Nullable String guid = null;

  private @Nullable String documentGuid = null;

  private @Nullable String url = null;

  private @Nullable String description = null;

  public DocumentReferencePOST guid(@Nullable String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("guid")
  public @Nullable String getGuid() {
    return guid;
  }

  public void setGuid(@Nullable String guid) {
    this.guid = guid;
  }

  public DocumentReferencePOST documentGuid(@Nullable String documentGuid) {
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

  public DocumentReferencePOST url(@Nullable String url) {
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

  public DocumentReferencePOST description(@Nullable String description) {
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
    DocumentReferencePOST documentReferencePOST = (DocumentReferencePOST) o;
    return Objects.equals(this.guid, documentReferencePOST.guid) &&
        Objects.equals(this.documentGuid, documentReferencePOST.documentGuid) &&
        Objects.equals(this.url, documentReferencePOST.url) &&
        Objects.equals(this.description, documentReferencePOST.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, documentGuid, url, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentReferencePOST {\n");
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

    private DocumentReferencePOST instance;

    public Builder() {
      this(new DocumentReferencePOST());
    }

    protected Builder(DocumentReferencePOST instance) {
      this.instance = instance;
    }

    protected Builder copyOf(DocumentReferencePOST value) { 
      this.instance.setGuid(value.guid);
      this.instance.setDocumentGuid(value.documentGuid);
      this.instance.setUrl(value.url);
      this.instance.setDescription(value.description);
      return this;
    }

    public DocumentReferencePOST.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public DocumentReferencePOST.Builder documentGuid(String documentGuid) {
      this.instance.documentGuid(documentGuid);
      return this;
    }
    
    public DocumentReferencePOST.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public DocumentReferencePOST.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    /**
    * returns a built DocumentReferencePOST instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public DocumentReferencePOST build() {
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
  public static DocumentReferencePOST.Builder builder() {
    return new DocumentReferencePOST.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public DocumentReferencePOST.Builder toBuilder() {
    DocumentReferencePOST.Builder builder = new DocumentReferencePOST.Builder();
    return builder.copyOf(this);
  }

}

