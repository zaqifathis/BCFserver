package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CommentPUT
 */

@JsonTypeName("comment_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class CommentPUT {

  private String comment;

  private JsonNullable<String> viewpointGuid = JsonNullable.<String>undefined();

  public CommentPUT() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CommentPUT(String comment) {
    this.comment = comment;
  }

  public CommentPUT comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
   */
  @NotNull 
  @Schema(name = "comment", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("comment")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public CommentPUT viewpointGuid(String viewpointGuid) {
    this.viewpointGuid = JsonNullable.of(viewpointGuid);
    return this;
  }

  /**
   * Get viewpointGuid
   * @return viewpointGuid
   */
  
  @Schema(name = "viewpoint_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("viewpoint_guid")
  public JsonNullable<String> getViewpointGuid() {
    return viewpointGuid;
  }

  public void setViewpointGuid(JsonNullable<String> viewpointGuid) {
    this.viewpointGuid = viewpointGuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentPUT commentPUT = (CommentPUT) o;
    return Objects.equals(this.comment, commentPUT.comment) &&
        equalsNullable(this.viewpointGuid, commentPUT.viewpointGuid);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment, hashCodeNullable(viewpointGuid));
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
    sb.append("class CommentPUT {\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    viewpointGuid: ").append(toIndentedString(viewpointGuid)).append("\n");
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

    private CommentPUT instance;

    public Builder() {
      this(new CommentPUT());
    }

    protected Builder(CommentPUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CommentPUT value) { 
      this.instance.setComment(value.comment);
      this.instance.setViewpointGuid(value.viewpointGuid);
      return this;
    }

    public CommentPUT.Builder comment(String comment) {
      this.instance.comment(comment);
      return this;
    }
    
    public CommentPUT.Builder viewpointGuid(String viewpointGuid) {
      this.instance.viewpointGuid(viewpointGuid);
      return this;
    }
    
    public CommentPUT.Builder viewpointGuid(JsonNullable<String> viewpointGuid) {
      this.instance.viewpointGuid = viewpointGuid;
      return this;
    }
    
    /**
    * returns a built CommentPUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CommentPUT build() {
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
  public static CommentPUT.Builder builder() {
    return new CommentPUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CommentPUT.Builder toBuilder() {
    CommentPUT.Builder builder = new CommentPUT.Builder();
    return builder.copyOf(this);
  }

}

