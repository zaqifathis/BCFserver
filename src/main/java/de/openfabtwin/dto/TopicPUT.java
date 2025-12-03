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
 * TopicPUT
 */

@JsonTypeName("topic_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicPUT {

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

  public TopicPUT() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TopicPUT(String title) {
    this.title = title;
  }

  public TopicPUT topicType(String topicType) {
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

  public TopicPUT topicStatus(String topicStatus) {
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

  public TopicPUT referenceLinks(List<String> referenceLinks) {
    this.referenceLinks = JsonNullable.of(referenceLinks);
    return this;
  }

  public TopicPUT addReferenceLinksItem(String referenceLinksItem) {
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

  public TopicPUT title(String title) {
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

  public TopicPUT priority(String priority) {
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

  public TopicPUT index(Integer index) {
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

  public TopicPUT labels(List<String> labels) {
    this.labels = JsonNullable.of(labels);
    return this;
  }

  public TopicPUT addLabelsItem(String labelsItem) {
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

  public TopicPUT assignedTo(String assignedTo) {
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

  public TopicPUT stage(String stage) {
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

  public TopicPUT description(String description) {
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

  public TopicPUT bimSnippet(BimSnippet bimSnippet) {
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

  public TopicPUT dueDate(String dueDate) {
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
    TopicPUT topicPUT = (TopicPUT) o;
    return equalsNullable(this.topicType, topicPUT.topicType) &&
        equalsNullable(this.topicStatus, topicPUT.topicStatus) &&
        equalsNullable(this.referenceLinks, topicPUT.referenceLinks) &&
        Objects.equals(this.title, topicPUT.title) &&
        equalsNullable(this.priority, topicPUT.priority) &&
        equalsNullable(this.index, topicPUT.index) &&
        equalsNullable(this.labels, topicPUT.labels) &&
        equalsNullable(this.assignedTo, topicPUT.assignedTo) &&
        equalsNullable(this.stage, topicPUT.stage) &&
        equalsNullable(this.description, topicPUT.description) &&
        equalsNullable(this.bimSnippet, topicPUT.bimSnippet) &&
        equalsNullable(this.dueDate, topicPUT.dueDate);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(topicType), hashCodeNullable(topicStatus), hashCodeNullable(referenceLinks), title, hashCodeNullable(priority), hashCodeNullable(index), hashCodeNullable(labels), hashCodeNullable(assignedTo), hashCodeNullable(stage), hashCodeNullable(description), hashCodeNullable(bimSnippet), hashCodeNullable(dueDate));
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
    sb.append("class TopicPUT {\n");
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

    private TopicPUT instance;

    public Builder() {
      this(new TopicPUT());
    }

    protected Builder(TopicPUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(TopicPUT value) { 
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

    public TopicPUT.Builder topicType(String topicType) {
      this.instance.topicType(topicType);
      return this;
    }
    
    public TopicPUT.Builder topicType(JsonNullable<String> topicType) {
      this.instance.topicType = topicType;
      return this;
    }
    
    public TopicPUT.Builder topicStatus(String topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicPUT.Builder topicStatus(JsonNullable<String> topicStatus) {
      this.instance.topicStatus = topicStatus;
      return this;
    }
    
    public TopicPUT.Builder referenceLinks(List<String> referenceLinks) {
      this.instance.referenceLinks(referenceLinks);
      return this;
    }
    
    public TopicPUT.Builder referenceLinks(JsonNullable<List<String>> referenceLinks) {
      this.instance.referenceLinks = referenceLinks;
      return this;
    }
    
    public TopicPUT.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public TopicPUT.Builder priority(String priority) {
      this.instance.priority(priority);
      return this;
    }
    
    public TopicPUT.Builder priority(JsonNullable<String> priority) {
      this.instance.priority = priority;
      return this;
    }
    
    public TopicPUT.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public TopicPUT.Builder index(JsonNullable<Integer> index) {
      this.instance.index = index;
      return this;
    }
    
    public TopicPUT.Builder labels(List<String> labels) {
      this.instance.labels(labels);
      return this;
    }
    
    public TopicPUT.Builder labels(JsonNullable<List<String>> labels) {
      this.instance.labels = labels;
      return this;
    }
    
    public TopicPUT.Builder assignedTo(String assignedTo) {
      this.instance.assignedTo(assignedTo);
      return this;
    }
    
    public TopicPUT.Builder assignedTo(JsonNullable<String> assignedTo) {
      this.instance.assignedTo = assignedTo;
      return this;
    }
    
    public TopicPUT.Builder stage(String stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public TopicPUT.Builder stage(JsonNullable<String> stage) {
      this.instance.stage = stage;
      return this;
    }
    
    public TopicPUT.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public TopicPUT.Builder description(JsonNullable<String> description) {
      this.instance.description = description;
      return this;
    }
    
    public TopicPUT.Builder bimSnippet(BimSnippet bimSnippet) {
      this.instance.bimSnippet(bimSnippet);
      return this;
    }
    
    public TopicPUT.Builder bimSnippet(JsonNullable<BimSnippet> bimSnippet) {
      this.instance.bimSnippet = bimSnippet;
      return this;
    }
    
    public TopicPUT.Builder dueDate(String dueDate) {
      this.instance.dueDate(dueDate);
      return this;
    }
    
    public TopicPUT.Builder dueDate(JsonNullable<String> dueDate) {
      this.instance.dueDate = dueDate;
      return this;
    }
    
    /**
    * returns a built TopicPUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public TopicPUT build() {
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
  public static TopicPUT.Builder builder() {
    return new TopicPUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public TopicPUT.Builder toBuilder() {
    TopicPUT.Builder builder = new TopicPUT.Builder();
    return builder.copyOf(this);
  }

}

