package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for single related topic GET, BCF REST API.
 */

@Schema(name = "related_topic_GET", description = "Schema for single related topic GET, BCF REST API.")
@JsonTypeName("related_topic_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class RelatedTopicGET {

  private String relatedTopicGuid;

  public RelatedTopicGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RelatedTopicGET(String relatedTopicGuid) {
    this.relatedTopicGuid = relatedTopicGuid;
  }

  public RelatedTopicGET relatedTopicGuid(String relatedTopicGuid) {
    this.relatedTopicGuid = relatedTopicGuid;
    return this;
  }

  /**
   * Get relatedTopicGuid
   * @return relatedTopicGuid
   */
  @NotNull 
  @Schema(name = "related_topic_guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("related_topic_guid")
  public String getRelatedTopicGuid() {
    return relatedTopicGuid;
  }

  public void setRelatedTopicGuid(String relatedTopicGuid) {
    this.relatedTopicGuid = relatedTopicGuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedTopicGET relatedTopicGET = (RelatedTopicGET) o;
    return Objects.equals(this.relatedTopicGuid, relatedTopicGET.relatedTopicGuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relatedTopicGuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedTopicGET {\n");
    sb.append("    relatedTopicGuid: ").append(toIndentedString(relatedTopicGuid)).append("\n");
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

    private RelatedTopicGET instance;

    public Builder() {
      this(new RelatedTopicGET());
    }

    protected Builder(RelatedTopicGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RelatedTopicGET value) { 
      this.instance.setRelatedTopicGuid(value.relatedTopicGuid);
      return this;
    }

    public RelatedTopicGET.Builder relatedTopicGuid(String relatedTopicGuid) {
      this.instance.relatedTopicGuid(relatedTopicGuid);
      return this;
    }
    
    /**
    * returns a built RelatedTopicGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RelatedTopicGET build() {
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
  public static RelatedTopicGET.Builder builder() {
    return new RelatedTopicGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RelatedTopicGET.Builder toBuilder() {
    RelatedTopicGET.Builder builder = new RelatedTopicGET.Builder();
    return builder.copyOf(this);
  }

}

