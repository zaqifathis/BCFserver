package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CommentEventGET
 */

@JsonTypeName("comment_event_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class CommentEventGET {

  private String commentGuid;

  private String topicGuid;

  private String date;

  private String author;

  @Valid
  private List<@Valid EventAction> actions = new ArrayList<>();

  public CommentEventGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CommentEventGET(String commentGuid, String topicGuid, String date, String author) {
    this.commentGuid = commentGuid;
    this.topicGuid = topicGuid;
    this.date = date;
    this.author = author;
  }

  public CommentEventGET commentGuid(String commentGuid) {
    this.commentGuid = commentGuid;
    return this;
  }

  /**
   * Get commentGuid
   * @return commentGuid
   */
  @NotNull 
  @Schema(name = "comment_guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("comment_guid")
  public String getCommentGuid() {
    return commentGuid;
  }

  public void setCommentGuid(String commentGuid) {
    this.commentGuid = commentGuid;
  }

  public CommentEventGET topicGuid(String topicGuid) {
    this.topicGuid = topicGuid;
    return this;
  }

  /**
   * Get topicGuid
   * @return topicGuid
   */
  @NotNull 
  @Schema(name = "topic_guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("topic_guid")
  public String getTopicGuid() {
    return topicGuid;
  }

  public void setTopicGuid(String topicGuid) {
    this.topicGuid = topicGuid;
  }

  public CommentEventGET date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @NotNull 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public CommentEventGET author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   */
  @NotNull 
  @Schema(name = "author", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("author")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public CommentEventGET actions(List<@Valid EventAction> actions) {
    this.actions = actions;
    return this;
  }

  public CommentEventGET addActionsItem(EventAction actionsItem) {
    if (this.actions == null) {
      this.actions = new ArrayList<>();
    }
    this.actions.add(actionsItem);
    return this;
  }

  /**
   * Get actions
   * @return actions
   */
  @Valid @Size(min = 1) 
  @Schema(name = "actions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("actions")
  public List<@Valid EventAction> getActions() {
    return actions;
  }

  public void setActions(List<@Valid EventAction> actions) {
    this.actions = actions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentEventGET commentEventGET = (CommentEventGET) o;
    return Objects.equals(this.commentGuid, commentEventGET.commentGuid) &&
        Objects.equals(this.topicGuid, commentEventGET.topicGuid) &&
        Objects.equals(this.date, commentEventGET.date) &&
        Objects.equals(this.author, commentEventGET.author) &&
        Objects.equals(this.actions, commentEventGET.actions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentGuid, topicGuid, date, author, actions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentEventGET {\n");
    sb.append("    commentGuid: ").append(toIndentedString(commentGuid)).append("\n");
    sb.append("    topicGuid: ").append(toIndentedString(topicGuid)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
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

    private CommentEventGET instance;

    public Builder() {
      this(new CommentEventGET());
    }

    protected Builder(CommentEventGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CommentEventGET value) { 
      this.instance.setCommentGuid(value.commentGuid);
      this.instance.setTopicGuid(value.topicGuid);
      this.instance.setDate(value.date);
      this.instance.setAuthor(value.author);
      this.instance.setActions(value.actions);
      return this;
    }

    public CommentEventGET.Builder commentGuid(String commentGuid) {
      this.instance.commentGuid(commentGuid);
      return this;
    }
    
    public CommentEventGET.Builder topicGuid(String topicGuid) {
      this.instance.topicGuid(topicGuid);
      return this;
    }
    
    public CommentEventGET.Builder date(String date) {
      this.instance.date(date);
      return this;
    }
    
    public CommentEventGET.Builder author(String author) {
      this.instance.author(author);
      return this;
    }
    
    public CommentEventGET.Builder actions(List<EventAction> actions) {
      this.instance.actions(actions);
      return this;
    }
    
    /**
    * returns a built CommentEventGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CommentEventGET build() {
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
  public static CommentEventGET.Builder builder() {
    return new CommentEventGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CommentEventGET.Builder toBuilder() {
    CommentEventGET.Builder builder = new CommentEventGET.Builder();
    return builder.copyOf(this);
  }

}

