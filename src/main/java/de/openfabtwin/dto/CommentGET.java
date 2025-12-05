package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CommentGET
 */

@JsonTypeName("comment_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class CommentGET {

  private String guid;

  private String date;

  private String author;

  private String comment;

  private String topicGuid;

  private @Nullable String viewpointGuid = null;

  private @Nullable String replyToCommentGuid = null;

  private @Nullable String modifiedDate = null;

  private @Nullable String modifiedAuthor = null;

  private @Nullable CommentGETAuthorization authorization;

  public CommentGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CommentGET(String guid, String date, String author, String comment, String topicGuid) {
    this.guid = guid;
    this.date = date;
    this.author = author;
    this.comment = comment;
    this.topicGuid = topicGuid;
  }

  public CommentGET guid(String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  @NotNull 
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("guid")
  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public CommentGET date(String date) {
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

  public CommentGET author(String author) {
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

  public CommentGET comment(String comment) {
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

  public CommentGET topicGuid(String topicGuid) {
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

  public CommentGET viewpointGuid(@Nullable String viewpointGuid) {
    this.viewpointGuid = viewpointGuid;
    return this;
  }

  /**
   * Get viewpointGuid
   * @return viewpointGuid
   */
  
  @Schema(name = "viewpoint_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("viewpoint_guid")
  public @Nullable String getViewpointGuid() {
    return viewpointGuid;
  }

  public void setViewpointGuid(@Nullable String viewpointGuid) {
    this.viewpointGuid = viewpointGuid;
  }

  public CommentGET replyToCommentGuid(@Nullable String replyToCommentGuid) {
    this.replyToCommentGuid = replyToCommentGuid;
    return this;
  }

  /**
   * Get replyToCommentGuid
   * @return replyToCommentGuid
   */
  
  @Schema(name = "reply_to_comment_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reply_to_comment_guid")
  public @Nullable String getReplyToCommentGuid() {
    return replyToCommentGuid;
  }

  public void setReplyToCommentGuid(@Nullable String replyToCommentGuid) {
    this.replyToCommentGuid = replyToCommentGuid;
  }

  public CommentGET modifiedDate(@Nullable String modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  /**
   * Get modifiedDate
   * @return modifiedDate
   */
  
  @Schema(name = "modified_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("modified_date")
  public @Nullable String getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(@Nullable String modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public CommentGET modifiedAuthor(@Nullable String modifiedAuthor) {
    this.modifiedAuthor = modifiedAuthor;
    return this;
  }

  /**
   * Get modifiedAuthor
   * @return modifiedAuthor
   */
  
  @Schema(name = "modified_author", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("modified_author")
  public @Nullable String getModifiedAuthor() {
    return modifiedAuthor;
  }

  public void setModifiedAuthor(@Nullable String modifiedAuthor) {
    this.modifiedAuthor = modifiedAuthor;
  }

  public CommentGET authorization(@Nullable CommentGETAuthorization authorization) {
    this.authorization = authorization;
    return this;
  }

  /**
   * Get authorization
   * @return authorization
   */
  @Valid 
  @Schema(name = "authorization", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorization")
  public @Nullable CommentGETAuthorization getAuthorization() {
    return authorization;
  }

  public void setAuthorization(@Nullable CommentGETAuthorization authorization) {
    this.authorization = authorization;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentGET commentGET = (CommentGET) o;
    return Objects.equals(this.guid, commentGET.guid) &&
        Objects.equals(this.date, commentGET.date) &&
        Objects.equals(this.author, commentGET.author) &&
        Objects.equals(this.comment, commentGET.comment) &&
        Objects.equals(this.topicGuid, commentGET.topicGuid) &&
        Objects.equals(this.viewpointGuid, commentGET.viewpointGuid) &&
        Objects.equals(this.replyToCommentGuid, commentGET.replyToCommentGuid) &&
        Objects.equals(this.modifiedDate, commentGET.modifiedDate) &&
        Objects.equals(this.modifiedAuthor, commentGET.modifiedAuthor) &&
        Objects.equals(this.authorization, commentGET.authorization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, date, author, comment, topicGuid, viewpointGuid, replyToCommentGuid, modifiedDate, modifiedAuthor, authorization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentGET {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    topicGuid: ").append(toIndentedString(topicGuid)).append("\n");
    sb.append("    viewpointGuid: ").append(toIndentedString(viewpointGuid)).append("\n");
    sb.append("    replyToCommentGuid: ").append(toIndentedString(replyToCommentGuid)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    modifiedAuthor: ").append(toIndentedString(modifiedAuthor)).append("\n");
    sb.append("    authorization: ").append(toIndentedString(authorization)).append("\n");
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

    private CommentGET instance;

    public Builder() {
      this(new CommentGET());
    }

    protected Builder(CommentGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CommentGET value) { 
      this.instance.setGuid(value.guid);
      this.instance.setDate(value.date);
      this.instance.setAuthor(value.author);
      this.instance.setComment(value.comment);
      this.instance.setTopicGuid(value.topicGuid);
      this.instance.setViewpointGuid(value.viewpointGuid);
      this.instance.setReplyToCommentGuid(value.replyToCommentGuid);
      this.instance.setModifiedDate(value.modifiedDate);
      this.instance.setModifiedAuthor(value.modifiedAuthor);
      this.instance.setAuthorization(value.authorization);
      return this;
    }

    public CommentGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public CommentGET.Builder date(String date) {
      this.instance.date(date);
      return this;
    }
    
    public CommentGET.Builder author(String author) {
      this.instance.author(author);
      return this;
    }
    
    public CommentGET.Builder comment(String comment) {
      this.instance.comment(comment);
      return this;
    }
    
    public CommentGET.Builder topicGuid(String topicGuid) {
      this.instance.topicGuid(topicGuid);
      return this;
    }
    
    public CommentGET.Builder viewpointGuid(String viewpointGuid) {
      this.instance.viewpointGuid(viewpointGuid);
      return this;
    }
    
    public CommentGET.Builder replyToCommentGuid(String replyToCommentGuid) {
      this.instance.replyToCommentGuid(replyToCommentGuid);
      return this;
    }
    
    public CommentGET.Builder modifiedDate(String modifiedDate) {
      this.instance.modifiedDate(modifiedDate);
      return this;
    }
    
    public CommentGET.Builder modifiedAuthor(String modifiedAuthor) {
      this.instance.modifiedAuthor(modifiedAuthor);
      return this;
    }
    
    public CommentGET.Builder authorization(CommentGETAuthorization authorization) {
      this.instance.authorization(authorization);
      return this;
    }
    
    /**
    * returns a built CommentGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CommentGET build() {
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
  public static CommentGET.Builder builder() {
    return new CommentGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CommentGET.Builder toBuilder() {
    CommentGET.Builder builder = new CommentGET.Builder();
    return builder.copyOf(this);
  }

}

