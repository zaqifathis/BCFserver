package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * BimSnippet
 */

@JsonTypeName("bim_snippet")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class BimSnippet {

  private String snippetType;

  private String isExternal;

  private String reference;

  private String referenceSchema;

  public BimSnippet() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BimSnippet(String snippetType, String isExternal, String reference, String referenceSchema) {
    this.snippetType = snippetType;
    this.isExternal = isExternal;
    this.reference = reference;
    this.referenceSchema = referenceSchema;
  }

  public BimSnippet snippetType(String snippetType) {
    this.snippetType = snippetType;
    return this;
  }

  /**
   * Get snippetType
   * @return snippetType
   */
  @NotNull 
  @Schema(name = "snippet_type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("snippet_type")
  public String getSnippetType() {
    return snippetType;
  }

  public void setSnippetType(String snippetType) {
    this.snippetType = snippetType;
  }

  public BimSnippet isExternal(String isExternal) {
    this.isExternal = isExternal;
    return this;
  }

  /**
   * Get isExternal
   * @return isExternal
   */
  @NotNull 
  @Schema(name = "is_external", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("is_external")
  public String getIsExternal() {
    return isExternal;
  }

  public void setIsExternal(String isExternal) {
    this.isExternal = isExternal;
  }

  public BimSnippet reference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
   */
  @NotNull 
  @Schema(name = "reference", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("reference")
  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public BimSnippet referenceSchema(String referenceSchema) {
    this.referenceSchema = referenceSchema;
    return this;
  }

  /**
   * Get referenceSchema
   * @return referenceSchema
   */
  @NotNull 
  @Schema(name = "reference_schema", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("reference_schema")
  public String getReferenceSchema() {
    return referenceSchema;
  }

  public void setReferenceSchema(String referenceSchema) {
    this.referenceSchema = referenceSchema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BimSnippet bimSnippet = (BimSnippet) o;
    return Objects.equals(this.snippetType, bimSnippet.snippetType) &&
        Objects.equals(this.isExternal, bimSnippet.isExternal) &&
        Objects.equals(this.reference, bimSnippet.reference) &&
        Objects.equals(this.referenceSchema, bimSnippet.referenceSchema);
  }

  @Override
  public int hashCode() {
    return Objects.hash(snippetType, isExternal, reference, referenceSchema);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BimSnippet {\n");
    sb.append("    snippetType: ").append(toIndentedString(snippetType)).append("\n");
    sb.append("    isExternal: ").append(toIndentedString(isExternal)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    referenceSchema: ").append(toIndentedString(referenceSchema)).append("\n");
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

    private BimSnippet instance;

    public Builder() {
      this(new BimSnippet());
    }

    protected Builder(BimSnippet instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BimSnippet value) { 
      this.instance.setSnippetType(value.snippetType);
      this.instance.setIsExternal(value.isExternal);
      this.instance.setReference(value.reference);
      this.instance.setReferenceSchema(value.referenceSchema);
      return this;
    }

    public BimSnippet.Builder snippetType(String snippetType) {
      this.instance.snippetType(snippetType);
      return this;
    }
    
    public BimSnippet.Builder isExternal(String isExternal) {
      this.instance.isExternal(isExternal);
      return this;
    }
    
    public BimSnippet.Builder reference(String reference) {
      this.instance.reference(reference);
      return this;
    }
    
    public BimSnippet.Builder referenceSchema(String referenceSchema) {
      this.instance.referenceSchema(referenceSchema);
      return this;
    }
    
    /**
    * returns a built BimSnippet instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BimSnippet build() {
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
  public static BimSnippet.Builder builder() {
    return new BimSnippet.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BimSnippet.Builder toBuilder() {
    BimSnippet.Builder builder = new BimSnippet.Builder();
    return builder.copyOf(this);
  }

}

