package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * TopicGET
 */

@JsonTypeName("topic_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class TopicGET {

  private String guid;

  private @Nullable String serverAssigendId;

  private JsonNullable<String> topicType = JsonNullable.<String>undefined();

  private JsonNullable<String> topicStatus = JsonNullable.<String>undefined();

  @Valid
  private JsonNullable<List<String>> referenceLinks = JsonNullable.<List<String>>undefined();

  private String title;

  private JsonNullable<String> priority = JsonNullable.<String>undefined();

  private JsonNullable<Integer> index = JsonNullable.<Integer>undefined();

  @Valid
  private JsonNullable<List<String>> labels = JsonNullable.<List<String>>undefined();

  private String creationDate;

  private String creationAuthor;

  private @Nullable String modifiedDate;

  private JsonNullable<String> modifiedAuthor = JsonNullable.<String>undefined();

  private JsonNullable<String> assignedTo = JsonNullable.<String>undefined();

  private JsonNullable<String> stage = JsonNullable.<String>undefined();

  private JsonNullable<String> description = JsonNullable.<String>undefined();

  private JsonNullable<BimSnippet> bimSnippet = JsonNullable.<BimSnippet>undefined();

  private JsonNullable<String> dueDate = JsonNullable.<String>undefined();

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

  public TopicGET topicType(String topicType) {
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

  public TopicGET topicStatus(String topicStatus) {
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

  public TopicGET referenceLinks(List<String> referenceLinks) {
    this.referenceLinks = JsonNullable.of(referenceLinks);
    return this;
  }

  public TopicGET addReferenceLinksItem(String referenceLinksItem) {
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

  public TopicGET priority(String priority) {
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

  public TopicGET index(Integer index) {
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

  public TopicGET labels(List<String> labels) {
    this.labels = JsonNullable.of(labels);
    return this;
  }

  public TopicGET addLabelsItem(String labelsItem) {
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

  public TopicGET modifiedAuthor(String modifiedAuthor) {
    this.modifiedAuthor = JsonNullable.of(modifiedAuthor);
    return this;
  }

  /**
   * Get modifiedAuthor
   * @return modifiedAuthor
   */
  
  @Schema(name = "modified_author", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("modified_author")
  public JsonNullable<String> getModifiedAuthor() {
    return modifiedAuthor;
  }

  public void setModifiedAuthor(JsonNullable<String> modifiedAuthor) {
    this.modifiedAuthor = modifiedAuthor;
  }

  public TopicGET assignedTo(String assignedTo) {
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

  public TopicGET stage(String stage) {
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

  public TopicGET description(String description) {
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

  public TopicGET bimSnippet(BimSnippet bimSnippet) {
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

  public TopicGET dueDate(String dueDate) {
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
        equalsNullable(this.topicType, topicGET.topicType) &&
        equalsNullable(this.topicStatus, topicGET.topicStatus) &&
        equalsNullable(this.referenceLinks, topicGET.referenceLinks) &&
        Objects.equals(this.title, topicGET.title) &&
        equalsNullable(this.priority, topicGET.priority) &&
        equalsNullable(this.index, topicGET.index) &&
        equalsNullable(this.labels, topicGET.labels) &&
        Objects.equals(this.creationDate, topicGET.creationDate) &&
        Objects.equals(this.creationAuthor, topicGET.creationAuthor) &&
        Objects.equals(this.modifiedDate, topicGET.modifiedDate) &&
        equalsNullable(this.modifiedAuthor, topicGET.modifiedAuthor) &&
        equalsNullable(this.assignedTo, topicGET.assignedTo) &&
        equalsNullable(this.stage, topicGET.stage) &&
        equalsNullable(this.description, topicGET.description) &&
        equalsNullable(this.bimSnippet, topicGET.bimSnippet) &&
        equalsNullable(this.dueDate, topicGET.dueDate) &&
        Objects.equals(this.authorization, topicGET.authorization);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, serverAssigendId, hashCodeNullable(topicType), hashCodeNullable(topicStatus), hashCodeNullable(referenceLinks), title, hashCodeNullable(priority), hashCodeNullable(index), hashCodeNullable(labels), creationDate, creationAuthor, modifiedDate, hashCodeNullable(modifiedAuthor), hashCodeNullable(assignedTo), hashCodeNullable(stage), hashCodeNullable(description), hashCodeNullable(bimSnippet), hashCodeNullable(dueDate), authorization);
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
    
    public TopicGET.Builder topicType(JsonNullable<String> topicType) {
      this.instance.topicType = topicType;
      return this;
    }
    
    public TopicGET.Builder topicStatus(String topicStatus) {
      this.instance.topicStatus(topicStatus);
      return this;
    }
    
    public TopicGET.Builder topicStatus(JsonNullable<String> topicStatus) {
      this.instance.topicStatus = topicStatus;
      return this;
    }
    
    public TopicGET.Builder referenceLinks(List<String> referenceLinks) {
      this.instance.referenceLinks(referenceLinks);
      return this;
    }
    
    public TopicGET.Builder referenceLinks(JsonNullable<List<String>> referenceLinks) {
      this.instance.referenceLinks = referenceLinks;
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
    
    public TopicGET.Builder priority(JsonNullable<String> priority) {
      this.instance.priority = priority;
      return this;
    }
    
    public TopicGET.Builder index(Integer index) {
      this.instance.index(index);
      return this;
    }
    
    public TopicGET.Builder index(JsonNullable<Integer> index) {
      this.instance.index = index;
      return this;
    }
    
    public TopicGET.Builder labels(List<String> labels) {
      this.instance.labels(labels);
      return this;
    }
    
    public TopicGET.Builder labels(JsonNullable<List<String>> labels) {
      this.instance.labels = labels;
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
    
    public TopicGET.Builder modifiedAuthor(JsonNullable<String> modifiedAuthor) {
      this.instance.modifiedAuthor = modifiedAuthor;
      return this;
    }
    
    public TopicGET.Builder assignedTo(String assignedTo) {
      this.instance.assignedTo(assignedTo);
      return this;
    }
    
    public TopicGET.Builder assignedTo(JsonNullable<String> assignedTo) {
      this.instance.assignedTo = assignedTo;
      return this;
    }
    
    public TopicGET.Builder stage(String stage) {
      this.instance.stage(stage);
      return this;
    }
    
    public TopicGET.Builder stage(JsonNullable<String> stage) {
      this.instance.stage = stage;
      return this;
    }
    
    public TopicGET.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public TopicGET.Builder description(JsonNullable<String> description) {
      this.instance.description = description;
      return this;
    }
    
    public TopicGET.Builder bimSnippet(BimSnippet bimSnippet) {
      this.instance.bimSnippet(bimSnippet);
      return this;
    }
    
    public TopicGET.Builder bimSnippet(JsonNullable<BimSnippet> bimSnippet) {
      this.instance.bimSnippet = bimSnippet;
      return this;
    }
    
    public TopicGET.Builder dueDate(String dueDate) {
      this.instance.dueDate(dueDate);
      return this;
    }
    
    public TopicGET.Builder dueDate(JsonNullable<String> dueDate) {
      this.instance.dueDate = dueDate;
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

