package de.openfabtwin.generated.dto;

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
 * ProjectGETAuthorization
 */

@JsonTypeName("project_GET_authorization")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ProjectGETAuthorization {

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

  public ProjectGETAuthorization projectActions(List<ProjectActionsEnum> projectActions) {
    this.projectActions = projectActions;
    return this;
  }

  public ProjectGETAuthorization addProjectActionsItem(ProjectActionsEnum projectActionsItem) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectGETAuthorization projectGETAuthorization = (ProjectGETAuthorization) o;
    return Objects.equals(this.projectActions, projectGETAuthorization.projectActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectActions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectGETAuthorization {\n");
    sb.append("    projectActions: ").append(toIndentedString(projectActions)).append("\n");
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

    private ProjectGETAuthorization instance;

    public Builder() {
      this(new ProjectGETAuthorization());
    }

    protected Builder(ProjectGETAuthorization instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProjectGETAuthorization value) { 
      this.instance.setProjectActions(value.projectActions);
      return this;
    }

    public ProjectGETAuthorization.Builder projectActions(List<ProjectActionsEnum> projectActions) {
      this.instance.projectActions(projectActions);
      return this;
    }
    
    /**
    * returns a built ProjectGETAuthorization instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProjectGETAuthorization build() {
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
  public static ProjectGETAuthorization.Builder builder() {
    return new ProjectGETAuthorization.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProjectGETAuthorization.Builder toBuilder() {
    ProjectGETAuthorization.Builder builder = new ProjectGETAuthorization.Builder();
    return builder.copyOf(this);
  }

}

