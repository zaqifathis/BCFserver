package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ExtensionsGET
 */

@JsonTypeName("extensions_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ExtensionsGET {

  @Valid
  private List<String> topicType;

  @Valid
  private List<String> topicStatus;

  @Valid
  private List<String> topicLabel;

  @Valid
  private List<String> snippetType;

  @Valid
  private List<String> priority;

  @Valid
  private List<String> users;

  @Valid
  private List<String> stage;

  /**
   * Gets or Sets projectActions
   */
  public enum ProjectActionsEnum {
    UPDATE("update"),
    
    CREATE_TOPIC("createTopic"),
    
    CREATE_DOCUMENT("createDocument");

    private final String value;

    ProjectActionsEnum(String value) {
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
    public static ProjectActionsEnum fromValue(String value) {
      for (ProjectActionsEnum b : ProjectActionsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Valid
  private List<ProjectActionsEnum> projectActions = new ArrayList<>();

  /**
   * Gets or Sets topicActions
   */
  public enum TopicActionsEnum {
    UPDATE("update"),
    
    UPDATE_BIM_SNIPPET("updateBimSnippet"),
    
    UPDATE_RELATED_TOPICS("updateRelatedTopics"),
    
    UPDATE_DOCUMENT_REFERENCES("updateDocumentReferences"),
    
    UPDATE_FILES("updateFiles"),
    
    CREATE_COMMENT("createComment"),
    
    CREATE_VIEWPOINT("createViewpoint"),
    
    DELETE("delete");

    private final String value;

    TopicActionsEnum(String value) {
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
    public static TopicActionsEnum fromValue(String value) {
      for (TopicActionsEnum b : TopicActionsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Valid
  private List<TopicActionsEnum> topicActions = new ArrayList<>();

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

  public ExtensionsGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ExtensionsGET(List<String> topicType, List<String> topicStatus, List<String> topicLabel, List<String> snippetType, List<String> priority, List<String> users, List<String> stage) {
    this.topicType = topicType;
    this.topicStatus = topicStatus;
    this.topicLabel = topicLabel;
    this.snippetType = snippetType;
    this.priority = priority;
    this.users = users;
    this.stage = stage;
  }

  public ExtensionsGET topicType(List<String> topicType) {
    this.topicType = topicType;
    return this;
  }

  public ExtensionsGET addTopicTypeItem(String topicTypeItem) {
    if (this.topicType == null) {
      this.topicType = new ArrayList<>();
    }
    this.topicType.add(topicTypeItem);
    return this;
  }

  /**
   * Get topicType
   * @return topicType
   */
  @NotNull 
  @Schema(name = "topic_type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("topic_type")
  public List<String> getTopicType() {
    return topicType;
  }

  public void setTopicType(List<String> topicType) {
    this.topicType = topicType;
  }

  public ExtensionsGET topicStatus(List<String> topicStatus) {
    this.topicStatus = topicStatus;
    return this;
  }

  public ExtensionsGET addTopicStatusItem(String topicStatusItem) {
    if (this.topicStatus == null) {
      this.topicStatus = new ArrayList<>();
    }
    this.topicStatus.add(topicStatusItem);
    return this;
  }

  /**
   * Get topicStatus
   * @return topicStatus
   */
  @NotNull 
  @Schema(name = "topic_status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("topic_status")
  public List<String> getTopicStatus() {
    return topicStatus;
  }

  public void setTopicStatus(List<String> topicStatus) {
    this.topicStatus = topicStatus;
  }

  public ExtensionsGET topicLabel(List<String> topicLabel) {
    this.topicLabel = topicLabel;
    return this;
  }

  public ExtensionsGET addTopicLabelItem(String topicLabelItem) {
    if (this.topicLabel == null) {
      this.topicLabel = new ArrayList<>();
    }
    this.topicLabel.add(topicLabelItem);
    return this;
  }

  /**
   * Get topicLabel
   * @return topicLabel
   */
  @NotNull 
  @Schema(name = "topic_label", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("topic_label")
  public List<String> getTopicLabel() {
    return topicLabel;
  }

  public void setTopicLabel(List<String> topicLabel) {
    this.topicLabel = topicLabel;
  }

  public ExtensionsGET snippetType(List<String> snippetType) {
    this.snippetType = snippetType;
    return this;
  }

  public ExtensionsGET addSnippetTypeItem(String snippetTypeItem) {
    if (this.snippetType == null) {
      this.snippetType = new ArrayList<>();
    }
    this.snippetType.add(snippetTypeItem);
    return this;
  }

  /**
   * Get snippetType
   * @return snippetType
   */
  @NotNull 
  @Schema(name = "snippet_type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("snippet_type")
  public List<String> getSnippetType() {
    return snippetType;
  }

  public void setSnippetType(List<String> snippetType) {
    this.snippetType = snippetType;
  }

  public ExtensionsGET priority(List<String> priority) {
    this.priority = priority;
    return this;
  }

  public ExtensionsGET addPriorityItem(String priorityItem) {
    if (this.priority == null) {
      this.priority = new ArrayList<>();
    }
    this.priority.add(priorityItem);
    return this;
  }

  /**
   * Get priority
   * @return priority
   */
  @NotNull 
  @Schema(name = "priority", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("priority")
  public List<String> getPriority() {
    return priority;
  }

  public void setPriority(List<String> priority) {
    this.priority = priority;
  }

  public ExtensionsGET users(List<String> users) {
    this.users = users;
    return this;
  }

  public ExtensionsGET addUsersItem(String usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
   */
  @NotNull 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("users")
  public List<String> getUsers() {
    return users;
  }

  public void setUsers(List<String> users) {
    this.users = users;
  }

  public ExtensionsGET stage(List<String> stage) {
    this.stage = stage;
    return this;
  }

  public ExtensionsGET addStageItem(String stageItem) {
    if (this.stage == null) {
      this.stage = new ArrayList<>();
    }
    this.stage.add(stageItem);
    return this;
  }

  /**
   * Get stage
   * @return stage
   */
  @NotNull 
  @Schema(name = "stage", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("stage")
  public List<String> getStage() {
    return stage;
  }

  public void setStage(List<String> stage) {
    this.stage = stage;
  }

  public ExtensionsGET projectActions(List<ProjectActionsEnum> projectActions) {
    this.projectActions = projectActions;
    return this;
  }

  public ExtensionsGET addProjectActionsItem(ProjectActionsEnum projectActionsItem) {
    if (this.projectActions == null) {
      this.projectActions = new ArrayList<>();
    }
    this.projectActions.add(projectActionsItem);
    return this;
  }

  /**
   * Get projectActions
   * @return projectActions
   */
  
  @Schema(name = "project_actions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("project_actions")
  public List<ProjectActionsEnum> getProjectActions() {
    return projectActions;
  }

  public void setProjectActions(List<ProjectActionsEnum> projectActions) {
    this.projectActions = projectActions;
  }

  public ExtensionsGET topicActions(List<TopicActionsEnum> topicActions) {
    this.topicActions = topicActions;
    return this;
  }

  public ExtensionsGET addTopicActionsItem(TopicActionsEnum topicActionsItem) {
    if (this.topicActions == null) {
      this.topicActions = new ArrayList<>();
    }
    this.topicActions.add(topicActionsItem);
    return this;
  }

  /**
   * Get topicActions
   * @return topicActions
   */
  
  @Schema(name = "topic_actions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_actions")
  public List<TopicActionsEnum> getTopicActions() {
    return topicActions;
  }

  public void setTopicActions(List<TopicActionsEnum> topicActions) {
    this.topicActions = topicActions;
  }

  public ExtensionsGET commentActions(List<CommentActionsEnum> commentActions) {
    this.commentActions = commentActions;
    return this;
  }

  public ExtensionsGET addCommentActionsItem(CommentActionsEnum commentActionsItem) {
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
    ExtensionsGET extensionsGET = (ExtensionsGET) o;
    return Objects.equals(this.topicType, extensionsGET.topicType) &&
        Objects.equals(this.topicStatus, extensionsGET.topicStatus) &&
        Objects.equals(this.topicLabel, extensionsGET.topicLabel) &&
        Objects.equals(this.snippetType, extensionsGET.snippetType) &&
        Objects.equals(this.priority, extensionsGET.priority) &&
        Objects.equals(this.users, extensionsGET.users) &&
        Objects.equals(this.stage, extensionsGET.stage) &&
        Objects.equals(this.projectActions, extensionsGET.projectActions) &&
        Objects.equals(this.topicActions, extensionsGET.topicActions) &&
        Objects.equals(this.commentActions, extensionsGET.commentActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicType, topicStatus, topicLabel, snippetType, priority, users, stage, projectActions, topicActions, commentActions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtensionsGET {\n");
    sb.append("    topicType: ").append(toIndentedString(topicType)).append("\n");
    sb.append("    topicStatus: ").append(toIndentedString(topicStatus)).append("\n");
    sb.append("    topicLabel: ").append(toIndentedString(topicLabel)).append("\n");
    sb.append("    snippetType: ").append(toIndentedString(snippetType)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    projectActions: ").append(toIndentedString(projectActions)).append("\n");
    sb.append("    topicActions: ").append(toIndentedString(topicActions)).append("\n");
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

    private ExtensionsGET instance;

    public Builder() {
      this(new ExtensionsGET());
    }

    protected Builder(ExtensionsGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ExtensionsGET value) { 
      this.instance.setTopicType(value.topicType);
      this.instance.setTopicStatus(value.topicStatus);
      this.instance.setTopicLabel(value.topicLabel);
      this.instance.setSnippetType(value.snippetType);
      this.instance.setPriority(value.priority);
      this.instance.setUsers(value.users);
      this.instance.setStage(value.stage);
      this.instance.setProjectActions(value.projectActions);
      this.instance.setTopicActions(value.topicActions);
      this.instance.setCommentActions(value.commentActions);
      return this;
    }

    public ExtensionsGET.Builder topicType(List<String> topicType) {
      this.instance.topicType(topicType);
      return this;
    }
    
    public ExtensionsGET.Builder topicStatus(List<String> topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public ExtensionsGET.Builder topicLabel(List<String> topicLabel) {
      this.instance.topicLabel(topicLabel);
      return this;
    }
    
    public ExtensionsGET.Builder snippetType(List<String> snippetType) {
      this.instance.snippetType(snippetType);
      return this;
    }
    
    public ExtensionsGET.Builder priority(List<String> priority) {
      this.instance.priority(priority);
      return this;
    }
    
    public ExtensionsGET.Builder users(List<String> users) {
      this.instance.users(users);
      return this;
    }
    
    public ExtensionsGET.Builder stage(List<String> stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public ExtensionsGET.Builder projectActions(List<ProjectActionsEnum> projectActions) {
      this.instance.projectActions(projectActions);
      return this;
    }
    
    public ExtensionsGET.Builder topicActions(List<TopicActionsEnum> topicActions) {
      this.instance.topicActions(topicActions);
      return this;
    }
    
    public ExtensionsGET.Builder commentActions(List<CommentActionsEnum> commentActions) {
      this.instance.commentActions(commentActions);
      return this;
    }
    
    /**
    * returns a built ExtensionsGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ExtensionsGET build() {
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
  public static ExtensionsGET.Builder builder() {
    return new ExtensionsGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ExtensionsGET.Builder toBuilder() {
    ExtensionsGET.Builder builder = new ExtensionsGET.Builder();
    return builder.copyOf(this);
  }

}

