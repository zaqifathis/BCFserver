package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * TopicPUT
 */

@JsonTypeName("topic_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicPUT {

  private @Nullable String topicType = null;

  private @Nullable String topicStatus = null;

  @Valid
  private @Nullable List<String> referenceLinks;

  private String title;

  private @Nullable String priority = null;

  private @Nullable Integer index = null;

  @Valid
  private @Nullable List<String> labels;

  private @Nullable String assignedTo = null;

  private @Nullable String stage = null;

  private @Nullable String description = null;

  private @Nullable BimSnippet bimSnippet = null;

  private @Nullable String dueDate = null;

  public TopicPUT() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TopicPUT(String title) {
    this.title = title;
  }

  public TopicPUT topicType(@Nullable String topicType) {
    this.topicType = topicType;
    return this;
  }

  /**
   * Get topicType
   * @return topicType
   */
  
  @Schema(name = "topic_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_type")
  public @Nullable String getTopicType() {
    return topicType;
  }

  public void setTopicType(@Nullable String topicType) {
    this.topicType = topicType;
  }

  public TopicPUT topicStatus(@Nullable String topicStatus) {
    this.topicStatus = topicStatus;
    return this;
  }

  /**
   * Get topicStatus
   * @return topicStatus
   */
  
  @Schema(name = "topic_status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topic_status")
  public @Nullable String getTopicStatus() {
    return topicStatus;
  }

  public void setTopicStatus(@Nullable String topicStatus) {
    this.topicStatus = topicStatus;
  }

  public TopicPUT referenceLinks(@Nullable List<String> referenceLinks) {
    this.referenceLinks = referenceLinks;
    return this;
  }

  public TopicPUT addReferenceLinksItem(String referenceLinksItem) {
    if (this.referenceLinks == null) {
      this.referenceLinks = new ArrayList<>();
    }
    this.referenceLinks.add(referenceLinksItem);
    return this;
  }

  /**
   * Get referenceLinks
   * @return referenceLinks
   */
  
  @Schema(name = "reference_links", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reference_links")
  public @Nullable List<String> getReferenceLinks() {
    return referenceLinks;
  }

  public void setReferenceLinks(@Nullable List<String> referenceLinks) {
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

  public TopicPUT priority(@Nullable String priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
   */
  
  @Schema(name = "priority", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priority")
  public @Nullable String getPriority() {
    return priority;
  }

  public void setPriority(@Nullable String priority) {
    this.priority = priority;
  }

  public TopicPUT index(@Nullable Integer index) {
    this.index = index;
    return this;
  }

  /**
   * Get index
   * @return index
   */
  
  @Schema(name = "index", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("index")
  public @Nullable Integer getIndex() {
    return index;
  }

  public void setIndex(@Nullable Integer index) {
    this.index = index;
  }

  public TopicPUT labels(@Nullable List<String> labels) {
    this.labels = labels;
    return this;
  }

  public TopicPUT addLabelsItem(String labelsItem) {
    if (this.labels == null) {
      this.labels = new ArrayList<>();
    }
    this.labels.add(labelsItem);
    return this;
  }

  /**
   * Get labels
   * @return labels
   */
  
  @Schema(name = "labels", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("labels")
  public @Nullable List<String> getLabels() {
    return labels;
  }

  public void setLabels(@Nullable List<String> labels) {
    this.labels = labels;
  }

  public TopicPUT assignedTo(@Nullable String assignedTo) {
    this.assignedTo = assignedTo;
    return this;
  }

  /**
   * Get assignedTo
   * @return assignedTo
   */
  
  @Schema(name = "assigned_to", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assigned_to")
  public @Nullable String getAssignedTo() {
    return assignedTo;
  }

  public void setAssignedTo(@Nullable String assignedTo) {
    this.assignedTo = assignedTo;
  }

  public TopicPUT stage(@Nullable String stage) {
    this.stage = stage;
    return this;
  }

  /**
   * Get stage
   * @return stage
   */
  
  @Schema(name = "stage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stage")
  public @Nullable String getStage() {
    return stage;
  }

  public void setStage(@Nullable String stage) {
    this.stage = stage;
  }

  public TopicPUT description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public TopicPUT bimSnippet(@Nullable BimSnippet bimSnippet) {
    this.bimSnippet = bimSnippet;
    return this;
  }

  /**
   * Get bimSnippet
   * @return bimSnippet
   */
  @Valid 
  @Schema(name = "bim_snippet", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bim_snippet")
  public @Nullable BimSnippet getBimSnippet() {
    return bimSnippet;
  }

  public void setBimSnippet(@Nullable BimSnippet bimSnippet) {
    this.bimSnippet = bimSnippet;
  }

  public TopicPUT dueDate(@Nullable String dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
   */
  
  @Schema(name = "due_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("due_date")
  public @Nullable String getDueDate() {
    return dueDate;
  }

  public void setDueDate(@Nullable String dueDate) {
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
    return Objects.equals(this.topicType, topicPUT.topicType) &&
        Objects.equals(this.topicStatus, topicPUT.topicStatus) &&
        Objects.equals(this.referenceLinks, topicPUT.referenceLinks) &&
        Objects.equals(this.title, topicPUT.title) &&
        Objects.equals(this.priority, topicPUT.priority) &&
        Objects.equals(this.index, topicPUT.index) &&
        Objects.equals(this.labels, topicPUT.labels) &&
        Objects.equals(this.assignedTo, topicPUT.assignedTo) &&
        Objects.equals(this.stage, topicPUT.stage) &&
        Objects.equals(this.description, topicPUT.description) &&
        Objects.equals(this.bimSnippet, topicPUT.bimSnippet) &&
        Objects.equals(this.dueDate, topicPUT.dueDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicType, topicStatus, referenceLinks, title, priority, index, labels, assignedTo, stage, description, bimSnippet, dueDate);
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
    
    public TopicPUT.Builder topicStatus(String topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicPUT.Builder referenceLinks(List<String> referenceLinks) {
      this.instance.referenceLinks(referenceLinks);
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
    
    public TopicPUT.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public TopicPUT.Builder labels(List<String> labels) {
      this.instance.labels(labels);
      return this;
    }
    
    public TopicPUT.Builder assignedTo(String assignedTo) {
      this.instance.assignedTo(assignedTo);
      return this;
    }
    
    public TopicPUT.Builder stage(String stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public TopicPUT.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public TopicPUT.Builder bimSnippet(BimSnippet bimSnippet) {
      this.instance.bimSnippet(bimSnippet);
      return this;
    }
    
    public TopicPUT.Builder dueDate(String dueDate) {
      this.instance.dueDate(dueDate);
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

