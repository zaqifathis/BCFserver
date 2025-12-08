package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * DocumentReferencePUT
 */

@JsonTypeName("document_reference_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentReferencePUT {

  private @Nullable String documentGuid = null;

  private @Nullable String url = null;

  private @Nullable String description = null;

  public DocumentReferencePUT documentGuid(@Nullable String documentGuid) {
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

  public DocumentReferencePUT url(@Nullable String url) {
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

  public DocumentReferencePUT description(@Nullable String description) {
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
    DocumentReferencePUT documentReferencePUT = (DocumentReferencePUT) o;
    return Objects.equals(this.documentGuid, documentReferencePUT.documentGuid) &&
        Objects.equals(this.url, documentReferencePUT.url) &&
        Objects.equals(this.description, documentReferencePUT.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentGuid, url, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentReferencePUT {\n");
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

    private DocumentReferencePUT instance;

    public Builder() {
      this(new DocumentReferencePUT());
    }

    protected Builder(DocumentReferencePUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(DocumentReferencePUT value) { 
      this.instance.setDocumentGuid(value.documentGuid);
      this.instance.setUrl(value.url);
      this.instance.setDescription(value.description);
      return this;
    }

    public DocumentReferencePUT.Builder documentGuid(String documentGuid) {
      this.instance.documentGuid(documentGuid);
      return this;
    }
    
    public DocumentReferencePUT.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public DocumentReferencePUT.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    /**
    * returns a built DocumentReferencePUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public DocumentReferencePUT build() {
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
  public static DocumentReferencePUT.Builder builder() {
    return new DocumentReferencePUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public DocumentReferencePUT.Builder toBuilder() {
    DocumentReferencePUT.Builder builder = new DocumentReferencePUT.Builder();
    return builder.copyOf(this);
  }

}

