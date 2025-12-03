package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * DocumentReferencePUT
 */

@JsonTypeName("document_reference_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentReferencePUT {

  private JsonNullable<String> documentGuid = JsonNullable.<String>undefined();

  private JsonNullable<String> url = JsonNullable.<String>undefined();

  private JsonNullable<String> description = JsonNullable.<String>undefined();

  public DocumentReferencePUT documentGuid(String documentGuid) {
    this.documentGuid = JsonNullable.of(documentGuid);
    return this;
  }

  /**
   * Get documentGuid
   * @return documentGuid
   */
  
  @Schema(name = "document_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("document_guid")
  public JsonNullable<String> getDocumentGuid() {
    return documentGuid;
  }

  public void setDocumentGuid(JsonNullable<String> documentGuid) {
    this.documentGuid = documentGuid;
  }

  public DocumentReferencePUT url(String url) {
    this.url = JsonNullable.of(url);
    return this;
  }

  /**
   * Get url
   * @return url
   */
  
  @Schema(name = "url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public JsonNullable<String> getUrl() {
    return url;
  }

  public void setUrl(JsonNullable<String> url) {
    this.url = url;
  }

  public DocumentReferencePUT description(String description) {
    this.description = JsonNullable.of(description);
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public JsonNullable<String> getDescription() {
    return description;
  }

  public void setDescription(JsonNullable<String> description) {
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
    return equalsNullable(this.documentGuid, documentReferencePUT.documentGuid) &&
        equalsNullable(this.url, documentReferencePUT.url) &&
        equalsNullable(this.description, documentReferencePUT.description);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(documentGuid), hashCodeNullable(url), hashCodeNullable(description));
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
    
    public DocumentReferencePUT.Builder documentGuid(JsonNullable<String> documentGuid) {
      this.instance.documentGuid = documentGuid;
      return this;
    }
    
    public DocumentReferencePUT.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public DocumentReferencePUT.Builder url(JsonNullable<String> url) {
      this.instance.url = url;
      return this;
    }
    
    public DocumentReferencePUT.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public DocumentReferencePUT.Builder description(JsonNullable<String> description) {
      this.instance.description = description;
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

