package de.openfabtwin.generated.dto;

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
 * TopicGET
 */

@JsonTypeName("topic_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicGET {

  private String guid;

  private @Nullable String serverAssigendId;

  private @Nullable String topicType = null;

  private @Nullable String topicStatus = null;

  @Valid
  private @Nullable List<String> referenceLinks;

  private String title;

  private @Nullable String priority = null;

  private @Nullable Integer index = null;

  @Valid
  private @Nullable List<String> labels;

  private String creationDate;

  private String creationAuthor;

  private @Nullable String modifiedDate;

  private @Nullable String modifiedAuthor = null;

  private @Nullable String assignedTo = null;

  private @Nullable String stage = null;

  private @Nullable String description = null;

  private @Nullable BimSnippet bimSnippet = null;

  private @Nullable String dueDate = null;

  private @Nullable TopicGETAuthorization authorization;

  public TopicGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TopicGET(String guid, String title, String creationDate, String creationAuthor) {
    this.guid = guid;
    this.title = title;
    this.creationDate = creationDate;
    this.creationAuthor = creationAuthor;
  }

  public TopicGET guid(String guid) {
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

  public TopicGET serverAssigendId(@Nullable String serverAssigendId) {
    this.serverAssigendId = serverAssigendId;
    return this;
  }

  /**
   * Get serverAssigendId
   * @return serverAssigendId
   */
  
  @Schema(name = "server_assigend_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("server_assigend_id")
  public @Nullable String getServerAssigendId() {
    return serverAssigendId;
  }

  public void setServerAssigendId(@Nullable String serverAssigendId) {
    this.serverAssigendId = serverAssigendId;
  }

  public TopicGET topicType(@Nullable String topicType) {
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

  public TopicGET topicStatus(@Nullable String topicStatus) {
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

  public TopicGET referenceLinks(@Nullable List<String> referenceLinks) {
    this.referenceLinks = referenceLinks;
    return this;
  }

  public TopicGET addReferenceLinksItem(String referenceLinksItem) {
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

  public TopicGET title(String title) {
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

  public TopicGET priority(@Nullable String priority) {
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

  public TopicGET index(@Nullable Integer index) {
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

  public TopicGET labels(@Nullable List<String> labels) {
    this.labels = labels;
    return this;
  }

  public TopicGET addLabelsItem(String labelsItem) {
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

  public TopicGET creationDate(String creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
   */
  @NotNull 
  @Schema(name = "creation_date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creation_date")
  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public TopicGET creationAuthor(String creationAuthor) {
    this.creationAuthor = creationAuthor;
    return this;
  }

  /**
   * Get creationAuthor
   * @return creationAuthor
   */
  @NotNull 
  @Schema(name = "creation_author", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creation_author")
  public String getCreationAuthor() {
    return creationAuthor;
  }

  public void setCreationAuthor(String creationAuthor) {
    this.creationAuthor = creationAuthor;
  }

  public TopicGET modifiedDate(@Nullable String modifiedDate) {
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

  public TopicGET modifiedAuthor(@Nullable String modifiedAuthor) {
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

  public TopicGET assignedTo(@Nullable String assignedTo) {
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

  public TopicGET stage(@Nullable String stage) {
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

  public TopicGET description(@Nullable String description) {
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

  public TopicGET bimSnippet(@Nullable BimSnippet bimSnippet) {
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

  public TopicGET dueDate(@Nullable String dueDate) {
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

  public TopicGET authorization(@Nullable TopicGETAuthorization authorization) {
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
  public @Nullable TopicGETAuthorization getAuthorization() {
    return authorization;
  }

  public void setAuthorization(@Nullable TopicGETAuthorization authorization) {
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
    TopicGET topicGET = (TopicGET) o;
    return Objects.equals(this.guid, topicGET.guid) &&
        Objects.equals(this.serverAssigendId, topicGET.serverAssigendId) &&
        Objects.equals(this.topicType, topicGET.topicType) &&
        Objects.equals(this.topicStatus, topicGET.topicStatus) &&
        Objects.equals(this.referenceLinks, topicGET.referenceLinks) &&
        Objects.equals(this.title, topicGET.title) &&
        Objects.equals(this.priority, topicGET.priority) &&
        Objects.equals(this.index, topicGET.index) &&
        Objects.equals(this.labels, topicGET.labels) &&
        Objects.equals(this.creationDate, topicGET.creationDate) &&
        Objects.equals(this.creationAuthor, topicGET.creationAuthor) &&
        Objects.equals(this.modifiedDate, topicGET.modifiedDate) &&
        Objects.equals(this.modifiedAuthor, topicGET.modifiedAuthor) &&
        Objects.equals(this.assignedTo, topicGET.assignedTo) &&
        Objects.equals(this.stage, topicGET.stage) &&
        Objects.equals(this.description, topicGET.description) &&
        Objects.equals(this.bimSnippet, topicGET.bimSnippet) &&
        Objects.equals(this.dueDate, topicGET.dueDate) &&
        Objects.equals(this.authorization, topicGET.authorization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, serverAssigendId, topicType, topicStatus, referenceLinks, title, priority, index, labels, creationDate, creationAuthor, modifiedDate, modifiedAuthor, assignedTo, stage, description, bimSnippet, dueDate, authorization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicGET {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    serverAssigendId: ").append(toIndentedString(serverAssigendId)).append("\n");
    sb.append("    topicType: ").append(toIndentedString(topicType)).append("\n");
    sb.append("    topicStatus: ").append(toIndentedString(topicStatus)).append("\n");
    sb.append("    referenceLinks: ").append(toIndentedString(referenceLinks)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    creationAuthor: ").append(toIndentedString(creationAuthor)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    modifiedAuthor: ").append(toIndentedString(modifiedAuthor)).append("\n");
    sb.append("    assignedTo: ").append(toIndentedString(assignedTo)).append("\n");
    sb.append("    stage: ").append(toIndentedString(stage)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    bimSnippet: ").append(toIndentedString(bimSnippet)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
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

    private TopicGET instance;

    public Builder() {
      this(new TopicGET());
    }

    protected Builder(TopicGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(TopicGET value) { 
      this.instance.setGuid(value.guid);
      this.instance.setServerAssigendId(value.serverAssigendId);
      this.instance.setTopicType(value.topicType);
      this.instance.setTopicStatus(value.topicStatus);
      this.instance.setReferenceLinks(value.referenceLinks);
      this.instance.setTitle(value.title);
      this.instance.setPriority(value.priority);
      this.instance.setIndex(value.index);
      this.instance.setLabels(value.labels);
      this.instance.setCreationDate(value.creationDate);
      this.instance.setCreationAuthor(value.creationAuthor);
      this.instance.setModifiedDate(value.modifiedDate);
      this.instance.setModifiedAuthor(value.modifiedAuthor);
      this.instance.setAssignedTo(value.assignedTo);
      this.instance.setStage(value.stage);
      this.instance.setDescription(value.description);
      this.instance.setBimSnippet(value.bimSnippet);
      this.instance.setDueDate(value.dueDate);
      this.instance.setAuthorization(value.authorization);
      return this;
    }

    public TopicGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public TopicGET.Builder serverAssigendId(String serverAssigendId) {
      this.instance.serverAssigendId(serverAssigendId);
      return this;
    }
    
    public TopicGET.Builder topicType(String topicType) {
      this.instance.topicType(topicType);
      return this;
    }
    
    public TopicGET.Builder topicStatus(String topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicGET.Builder referenceLinks(List<String> referenceLinks) {
      this.instance.referenceLinks(referenceLinks);
      return this;
    }
    
    public TopicGET.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public TopicGET.Builder priority(String priority) {
      this.instance.priority(priority);
      return this;
    }
    
    public TopicGET.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public TopicGET.Builder labels(List<String> labels) {
      this.instance.labels(labels);
      return this;
    }
    
    public TopicGET.Builder creationDate(String creationDate) {
      this.instance.creationDate(creationDate);
      return this;
    }
    
    public TopicGET.Builder creationAuthor(String creationAuthor) {
      this.instance.creationAuthor(creationAuthor);
      return this;
    }
    
    public TopicGET.Builder modifiedDate(String modifiedDate) {
      this.instance.modifiedDate(modifiedDate);
      return this;
    }
    
    public TopicGET.Builder modifiedAuthor(String modifiedAuthor) {
      this.instance.modifiedAuthor(modifiedAuthor);
      return this;
    }
    
    public TopicGET.Builder assignedTo(String assignedTo) {
      this.instance.assignedTo(assignedTo);
      return this;
    }
    
    public TopicGET.Builder stage(String stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public TopicGET.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public TopicGET.Builder bimSnippet(BimSnippet bimSnippet) {
      this.instance.bimSnippet(bimSnippet);
      return this;
    }
    
    public TopicGET.Builder dueDate(String dueDate) {
      this.instance.dueDate(dueDate);
      return this;
    }
    
    public TopicGET.Builder authorization(TopicGETAuthorization authorization) {
      this.instance.authorization(authorization);
      return this;
    }
    
    /**
    * returns a built TopicGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public TopicGET build() {
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
  public static TopicGET.Builder builder() {
    return new TopicGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public TopicGET.Builder toBuilder() {
    TopicGET.Builder builder = new TopicGET.Builder();
    return builder.copyOf(this);
  }

}

