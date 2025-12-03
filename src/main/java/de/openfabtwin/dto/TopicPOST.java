package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * TopicPOST
 */

@JsonTypeName("topic_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicPOST {

  private JsonNullable<String> guid = JsonNullable.<String>undefined();

  private JsonNullable<String> topicType = JsonNullable.<String>undefined();

  private JsonNullable<String> topicStatus = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<List<String>> referenceLinks = JsonNullable.<List<String>>undefined();

  private String title;

  private JsonNullable<String> priority = JsonNullable.<String>undefined();

  private JsonNullable<Integer> index = JsonNullable.<Integer>undefined();

  @Valid
  private JsonNullable<List<String>> labels = JsonNullable.<List<String>>undefined();

  private JsonNullable<String> assignedTo = JsonNullable.<String>undefined();

  private JsonNullable<String> stage = JsonNullable.<String>undefined();

  private JsonNullable<String> description = JsonNullable.<String>undefined();

  private JsonNullable<BimSnippet> bimSnippet = JsonNullable.<BimSnippet>undefined();

  private JsonNullable<String> dueDate = JsonNullable.<String>undefined();

  public TopicPOST() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TopicPOST(String title) {
    this.title = title;
  }

  public TopicPOST guid(String guid) {
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

  public TopicPOST topicType(String topicType) {
    this.topicType = JsonNullable.of(topicType);
    return this;
  }

  /**
   * Get topicType
   * @return topicType
   */
  
  @Schema(name = "topic_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_type")
  public JsonNullable<String> getTopicType() {
    return topicType;
  }

  public void setTopicType(JsonNullable<String> topicType) {
    this.topicType = topicType;
  }

  public TopicPOST topicStatus(String topicStatus) {
    this.topicStatus = JsonNullable.of(topicStatus);
    return this;
  }

  /**
   * Get topicStatus
   * @return topicStatus
   */
  
  @Schema(name = "topic_status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_status")
  public JsonNullable<String> getTopicStatus() {
    return topicStatus;
  }

  public void setTopicStatus(JsonNullable<String> topicStatus) {
    this.topicStatus = topicStatus;
  }

  public TopicPOST referenceLinks(List<String> referenceLinks) {
    this.referenceLinks = JsonNullable.of(referenceLinks);
    return this;
  }

  public TopicPOST addReferenceLinksItem(String referenceLinksItem) {
    if (this.referenceLinks == null || !this.referenceLinks.isPresent()) {
      this.referenceLinks = JsonNullable.of(new ArrayList<>());
    }
    this.referenceLinks.get().add(referenceLinksItem);
    return this;
  }

  /**
   * Get referenceLinks
   * @return referenceLinks
   */
  
  @Schema(name = "reference_links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reference_links")
  public JsonNullable<List<String>> getReferenceLinks() {
    return referenceLinks;
  }

  public void setReferenceLinks(JsonNullable<List<String>> referenceLinks) {
    this.referenceLinks = referenceLinks;
  }

  public TopicPOST title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TopicPOST priority(String priority) {
    this.priority = JsonNullable.of(priority);
    return this;
  }

  /**
   * Get priority
   * @return priority
   */
  
  @Schema(name = "priority", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priority")
  public JsonNullable<String> getPriority() {
    return priority;
  }

  public void setPriority(JsonNullable<String> priority) {
    this.priority = priority;
  }

  public TopicPOST index(Integer index) {
    this.index = JsonNullable.of(index);
    return this;
  }

  /**
   * Get index
   * @return index
   */
  
  @Schema(name = "index", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("index")
  public JsonNullable<Integer> getIndex() {
    return index;
  }

  public void setIndex(JsonNullable<Integer> index) {
    this.index = index;
  }

  public TopicPOST labels(List<String> labels) {
    this.labels = JsonNullable.of(labels);
    return this;
  }

  public TopicPOST addLabelsItem(String labelsItem) {
    if (this.labels == null || !this.labels.isPresent()) {
      this.labels = JsonNullable.of(new ArrayList<>());
    }
    this.labels.get().add(labelsItem);
    return this;
  }

  /**
   * Get labels
   * @return labels
   */
  
  @Schema(name = "labels", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("labels")
  public JsonNullable<List<String>> getLabels() {
    return labels;
  }

  public void setLabels(JsonNullable<List<String>> labels) {
    this.labels = labels;
  }

  public TopicPOST assignedTo(String assignedTo) {
    this.assignedTo = JsonNullable.of(assignedTo);
    return this;
  }

  /**
   * Get assignedTo
   * @return assignedTo
   */
  
  @Schema(name = "assigned_to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assigned_to")
  public JsonNullable<String> getAssignedTo() {
    return assignedTo;
  }

  public void setAssignedTo(JsonNullable<String> assignedTo) {
    this.assignedTo = assignedTo;
  }

  public TopicPOST stage(String stage) {
    this.stage = JsonNullable.of(stage);
    return this;
  }

  /**
   * Get stage
   * @return stage
   */
  
  @Schema(name = "stage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stage")
  public JsonNullable<String> getStage() {
    return stage;
  }

  public void setStage(JsonNullable<String> stage) {
    this.stage = stage;
  }

  public TopicPOST description(String description) {
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

  public TopicPOST bimSnippet(BimSnippet bimSnippet) {
    this.bimSnippet = JsonNullable.of(bimSnippet);
    return this;
  }

  /**
   * Get bimSnippet
   * @return bimSnippet
   */
  @Valid 
  @Schema(name = "bim_snippet", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bim_snippet")
  public JsonNullable<BimSnippet> getBimSnippet() {
    return bimSnippet;
  }

  public void setBimSnippet(JsonNullable<BimSnippet> bimSnippet) {
    this.bimSnippet = bimSnippet;
  }

  public TopicPOST dueDate(String dueDate) {
    this.dueDate = JsonNullable.of(dueDate);
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
   */
  
  @Schema(name = "due_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("due_date")
  public JsonNullable<String> getDueDate() {
    return dueDate;
  }

  public void setDueDate(JsonNullable<String> dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicPOST topicPOST = (TopicPOST) o;
    return equalsNullable(this.guid, topicPOST.guid) &&
        equalsNullable(this.topicType, topicPOST.topicType) &&
        equalsNullable(this.topicStatus, topicPOST.topicStatus) &&
        equalsNullable(this.referenceLinks, topicPOST.referenceLinks) &&
        Objects.equals(this.title, topicPOST.title) &&
        equalsNullable(this.priority, topicPOST.priority) &&
        equalsNullable(this.index, topicPOST.index) &&
        equalsNullable(this.labels, topicPOST.labels) &&
        equalsNullable(this.assignedTo, topicPOST.assignedTo) &&
        equalsNullable(this.stage, topicPOST.stage) &&
        equalsNullable(this.description, topicPOST.description) &&
        equalsNullable(this.bimSnippet, topicPOST.bimSnippet) &&
        equalsNullable(this.dueDate, topicPOST.dueDate);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(guid), hashCodeNullable(topicType), hashCodeNullable(topicStatus), hashCodeNullable(referenceLinks), title, hashCodeNullable(priority), hashCodeNullable(index), hashCodeNullable(labels), hashCodeNullable(assignedTo), hashCodeNullable(stage), hashCodeNullable(description), hashCodeNullable(bimSnippet), hashCodeNullable(dueDate));
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
    sb.append("class TopicPOST {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    topicType: ").append(toIndentedString(topicType)).append("\n");
    sb.append("    topicStatus: ").append(toIndentedString(topicStatus)).append("\n");
    sb.append("    referenceLinks: ").append(toIndentedString(referenceLinks)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    assignedTo: ").append(toIndentedString(assignedTo)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    bimSnippet: ").append(toIndentedString(bimSnippet)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
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

    private TopicPOST instance;

    public Builder() {
      this(new TopicPOST());
    }

    protected Builder(TopicPOST instance) {
      this.instance = instance;
    }

    protected Builder copyOf(TopicPOST value) { 
      this.instance.setGuid(value.guid);
      this.instance.setTopicType(value.topicType);
      this.instance.setTopicStatus(value.topicStatus);
      this.instance.setReferenceLinks(value.referenceLinks);
      this.instance.setTitle(value.title);
      this.instance.setPriority(value.priority);
      this.instance.setIndex(value.index);
      this.instance.setLabels(value.labels);
      this.instance.setAssignedTo(value.assignedTo);
      this.instance.setStage(value.stage);
      this.instance.setDescription(value.description);
      this.instance.setBimSnippet(value.bimSnippet);
      this.instance.setDueDate(value.dueDate);
      return this;
    }

    public TopicPOST.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public TopicPOST.Builder guid(JsonNullable<String> guid) {
      this.instance.guid = guid;
      return this;
    }
    
    public TopicPOST.Builder topicType(String topicType) {
      this.instance.topicType(topicType);
      return this;
    }
    
    public TopicPOST.Builder topicType(JsonNullable<String> topicType) {
      this.instance.topicType = topicType;
      return this;
    }
    
    public TopicPOST.Builder topicStatus(String topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicPOST.Builder topicStatus(JsonNullable<String> topicStatus) {
      this.instance.topicStatus = topicStatus;
      return this;
    }
    
    public TopicPOST.Builder referenceLinks(List<String> referenceLinks) {
      this.instance.referenceLinks(referenceLinks);
      return this;
    }
    
    public TopicPOST.Builder referenceLinks(JsonNullable<List<String>> referenceLinks) {
      this.instance.referenceLinks = referenceLinks;
      return this;
    }
    
    public TopicPOST.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public TopicPOST.Builder priority(String priority) {
      this.instance.priority(priority);
      return this;
    }
    
    public TopicPOST.Builder priority(JsonNullable<String> priority) {
      this.instance.priority = priority;
      return this;
    }
    
    public TopicPOST.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public TopicPOST.Builder index(JsonNullable<Integer> index) {
      this.instance.index = index;
      return this;
    }
    
    public TopicPOST.Builder labels(List<String> labels) {
      this.instance.labels(labels);
      return this;
    }
    
    public TopicPOST.Builder labels(JsonNullable<List<String>> labels) {
      this.instance.labels = labels;
      return this;
    }
    
    public TopicPOST.Builder assignedTo(String assignedTo) {
      this.instance.assignedTo(assignedTo);
      return this;
    }
    
    public TopicPOST.Builder assignedTo(JsonNullable<String> assignedTo) {
      this.instance.assignedTo = assignedTo;
      return this;
    }
    
    public TopicPOST.Builder stage(String stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public TopicPOST.Builder stage(JsonNullable<String> stage) {
      this.instance.stage = stage;
      return this;
    }
    
    public TopicPOST.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public TopicPOST.Builder description(JsonNullable<String> description) {
      this.instance.description = description;
      return this;
    }
    
    public TopicPOST.Builder bimSnippet(BimSnippet bimSnippet) {
      this.instance.bimSnippet(bimSnippet);
      return this;
    }
    
    public TopicPOST.Builder bimSnippet(JsonNullable<BimSnippet> bimSnippet) {
      this.instance.bimSnippet = bimSnippet;
      return this;
    }
    
    public TopicPOST.Builder dueDate(String dueDate) {
      this.instance.dueDate(dueDate);
      return this;
    }
    
    public TopicPOST.Builder dueDate(JsonNullable<String> dueDate) {
      this.instance.dueDate = dueDate;
      return this;
    }
    
    /**
    * returns a built TopicPOST instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public TopicPOST build() {
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
  public static TopicPOST.Builder builder() {
    return new TopicPOST.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public TopicPOST.Builder toBuilder() {
    TopicPOST.Builder builder = new TopicPOST.Builder();
    return builder.copyOf(this);
  }

}

