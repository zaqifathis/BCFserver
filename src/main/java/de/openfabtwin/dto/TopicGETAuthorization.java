package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * TopicGETAuthorization
 */

@JsonTypeName("topic_GET_authorization")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicGETAuthorization {

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

  @Valid
  private JsonNullable<List<String>> topicStatus = JsonNullable.<List<String>>undefined();

  public TopicGETAuthorization topicActions(List<TopicActionsEnum> topicActions) {
    this.topicActions = topicActions;
    return this;
  }

  public TopicGETAuthorization addTopicActionsItem(TopicActionsEnum topicActionsItem) {
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

  public TopicGETAuthorization topicStatus(List<String> topicStatus) {
    this.topicStatus = JsonNullable.of(topicStatus);
    return this;
  }

  public TopicGETAuthorization addTopicStatusItem(String topicStatusItem) {
    if (this.topicStatus == null || !this.topicStatus.isPresent()) {
      this.topicStatus = JsonNullable.of(new ArrayList<>());
    }
    this.topicStatus.get().add(topicStatusItem);
    return this;
  }

  /**
   * Get topicStatus
   * @return topicStatus
   */
  
  @Schema(name = "topic_status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_status")
  public JsonNullable<List<String>> getTopicStatus() {
    return topicStatus;
  }

  public void setTopicStatus(JsonNullable<List<String>> topicStatus) {
    this.topicStatus = topicStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicGETAuthorization topicGETAuthorization = (TopicGETAuthorization) o;
    return Objects.equals(this.topicActions, topicGETAuthorization.topicActions) &&
        equalsNullable(this.topicStatus, topicGETAuthorization.topicStatus);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicActions, hashCodeNullable(topicStatus));
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
    sb.append("class TopicGETAuthorization {\n");
    sb.append("    topicActions: ").append(toIndentedString(topicActions)).append("\n");
    sb.append("    topicStatus: ").append(toIndentedString(topicStatus)).append("\n");
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

    private TopicGETAuthorization instance;

    public Builder() {
      this(new TopicGETAuthorization());
    }

    protected Builder(TopicGETAuthorization instance) {
      this.instance = instance;
    }

    protected Builder copyOf(TopicGETAuthorization value) { 
      this.instance.setTopicActions(value.topicActions);
      this.instance.setTopicStatus(value.topicStatus);
      return this;
    }

    public TopicGETAuthorization.Builder topicActions(List<TopicActionsEnum> topicActions) {
      this.instance.topicActions(topicActions);
      return this;
    }
    
    public TopicGETAuthorization.Builder topicStatus(List<String> topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicGETAuthorization.Builder topicStatus(JsonNullable<List<String>> topicStatus) {
      this.instance.topicStatus = topicStatus;
      return this;
    }
    
    /**
    * returns a built TopicGETAuthorization instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public TopicGETAuthorization build() {
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
  public static TopicGETAuthorization.Builder builder() {
    return new TopicGETAuthorization.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public TopicGETAuthorization.Builder toBuilder() {
    TopicGETAuthorization.Builder builder = new TopicGETAuthorization.Builder();
    return builder.copyOf(this);
  }

}

