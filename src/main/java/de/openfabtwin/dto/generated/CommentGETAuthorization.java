package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CommentGETAuthorization
 */

@JsonTypeName("comment_GET_authorization")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class CommentGETAuthorization {

  /**
   * Gets or Sets commentActions
   */
  public enum CommentActionsEnum {
    UPDATE("update"),
    
    DELETE("delete");

    private final String value;

    CommentActionsEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CommentActionsEnum fromValue(String value) {
      for (CommentActionsEnum b : CommentActionsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Valid
  private List<CommentActionsEnum> commentActions = new ArrayList<>();

  public CommentGETAuthorization commentActions(List<CommentActionsEnum> commentActions) {
    this.commentActions = commentActions;
    return this;
  }

  public CommentGETAuthorization addCommentActionsItem(CommentActionsEnum commentActionsItem) {
    if (this.commentActions == null) {
      this.commentActions = new ArrayList<>();
    }
    this.commentActions.add(commentActionsItem);
    return this;
  }

  /**
   * Get commentActions
   * @return commentActions
   */
  
  @Schema(name = "comment_actions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("comment_actions")
  public List<CommentActionsEnum> getCommentActions() {
    return commentActions;
  }

  public void setCommentActions(List<CommentActionsEnum> commentActions) {
    this.commentActions = commentActions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentGETAuthorization commentGETAuthorization = (CommentGETAuthorization) o;
    return Objects.equals(this.commentActions, commentGETAuthorization.commentActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentActions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentGETAuthorization {\n");
    sb.append("    commentActions: ").append(toIndentedString(commentActions)).append("\n");
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

    private CommentGETAuthorization instance;

    public Builder() {
      this(new CommentGETAuthorization());
    }

    protected Builder(CommentGETAuthorization instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CommentGETAuthorization value) { 
      this.instance.setCommentActions(value.commentActions);
      return this;
    }

    public CommentGETAuthorization.Builder commentActions(List<CommentActionsEnum> commentActions) {
      this.instance.commentActions(commentActions);
      return this;
    }
    
    /**
    * returns a built CommentGETAuthorization instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CommentGETAuthorization build() {
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
  public static CommentGETAuthorization.Builder builder() {
    return new CommentGETAuthorization.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CommentGETAuthorization.Builder toBuilder() {
    CommentGETAuthorization.Builder builder = new CommentGETAuthorization.Builder();
    return builder.copyOf(this);
  }

}

