package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for related topic PUT, BCF REST API.
 */

@Schema(name = "related_topic_PUT", description = "Schema for related topic PUT, BCF REST API.")
@JsonTypeName("related_topic_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class RelatedTopicPUT {

  private String relatedTopicGuid;

  public RelatedTopicPUT() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RelatedTopicPUT(String relatedTopicGuid) {
    this.relatedTopicGuid = relatedTopicGuid;
  }

  public RelatedTopicPUT relatedTopicGuid(String relatedTopicGuid) {
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
    RelatedTopicPUT relatedTopicPUT = (RelatedTopicPUT) o;
    return Objects.equals(this.relatedTopicGuid, relatedTopicPUT.relatedTopicGuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relatedTopicGuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedTopicPUT {\n");
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

    private RelatedTopicPUT instance;

    public Builder() {
      this(new RelatedTopicPUT());
    }

    protected Builder(RelatedTopicPUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RelatedTopicPUT value) { 
      this.instance.setRelatedTopicGuid(value.relatedTopicGuid);
      return this;
    }

    public RelatedTopicPUT.Builder relatedTopicGuid(String relatedTopicGuid) {
      this.instance.relatedTopicGuid(relatedTopicGuid);
      return this;
    }
    
    /**
    * returns a built RelatedTopicPUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RelatedTopicPUT build() {
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
  public static RelatedTopicPUT.Builder builder() {
    return new RelatedTopicPUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RelatedTopicPUT.Builder toBuilder() {
    RelatedTopicPUT.Builder builder = new RelatedTopicPUT.Builder();
    return builder.copyOf(this);
  }

}

