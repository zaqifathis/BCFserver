package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * DocumentReferencePOST
 */

@JsonTypeName("document_reference_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class DocumentReferencePOST {

  private JsonNullable<String> guid = JsonNullable.<String>undefined();

  private JsonNullable<String> documentGuid = JsonNullable.<String>undefined();

  private JsonNullable<String> url = JsonNullable.<String>undefined();

  private JsonNullable<String> description = JsonNullable.<String>undefined();

  public DocumentReferencePOST guid(String guid) {
    this.guid = JsonNullable.of(guid);
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("guid")
  public JsonNullable<String> getGuid() {
    return guid;
  }

  public void setGuid(JsonNullable<String> guid) {
    this.guid = guid;
  }

  public DocumentReferencePOST documentGuid(String documentGuid) {
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

  public DocumentReferencePOST url(String url) {
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

  public DocumentReferencePOST description(String description) {
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
    DocumentReferencePOST documentReferencePOST = (DocumentReferencePOST) o;
    return equalsNullable(this.guid, documentReferencePOST.guid) &&
        equalsNullable(this.documentGuid, documentReferencePOST.documentGuid) &&
        equalsNullable(this.url, documentReferencePOST.url) &&
        equalsNullable(this.description, documentReferencePOST.description);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(guid), hashCodeNullable(documentGuid), hashCodeNullable(url), hashCodeNullable(description));
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
    
    public DocumentReferencePOST.Builder guid(JsonNullable<String> guid) {
      this.instance.guid = guid;
      return this;
    }
    
    public DocumentReferencePOST.Builder documentGuid(String documentGuid) {
      this.instance.documentGuid(documentGuid);
      return this;
    }
    
    public DocumentReferencePOST.Builder documentGuid(JsonNullable<String> documentGuid) {
      this.instance.documentGuid = documentGuid;
      return this;
    }
    
    public DocumentReferencePOST.Builder url(String url) {
      this.instance.url(url);
      return this;
    }
    
    public DocumentReferencePOST.Builder url(JsonNullable<String> url) {
      this.instance.url = url;
      return this;
    }
    
    public DocumentReferencePOST.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public DocumentReferencePOST.Builder description(JsonNullable<String> description) {
      this.instance.description = description;
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

