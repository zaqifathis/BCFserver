package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for single project GET, BCF REST API.
 */

@Schema(name = "project_GET", description = "Schema for single project GET, BCF REST API.")
@JsonTypeName("project_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ProjectGET {

  private String projectId;

  private String name;

  private @Nullable ProjectGETAuthorization authorization;

  public ProjectGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectGET(String projectId, String name) {
    this.projectId = projectId;
    this.name = name;
  }

  public ProjectGET projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * Get projectId
   * @return projectId
   */
  @NotNull 
  @Schema(name = "project_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("project_id")
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public ProjectGET name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProjectGET authorization(@Nullable ProjectGETAuthorization authorization) {
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
  public @Nullable ProjectGETAuthorization getAuthorization() {
    return authorization;
  }

  public void setAuthorization(@Nullable ProjectGETAuthorization authorization) {
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
    ProjectGET projectGET = (ProjectGET) o;
    return Objects.equals(this.projectId, projectGET.projectId) &&
        Objects.equals(this.name, projectGET.name) &&
        Objects.equals(this.authorization, projectGET.authorization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, name, authorization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectGET {\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

    private ProjectGET instance;

    public Builder() {
      this(new ProjectGET());
    }

    protected Builder(ProjectGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProjectGET value) { 
      this.instance.setProjectId(value.projectId);
      this.instance.setName(value.name);
      this.instance.setAuthorization(value.authorization);
      return this;
    }

    public ProjectGET.Builder projectId(String projectId) {
      this.instance.projectId(projectId);
      return this;
    }
    
    public ProjectGET.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public ProjectGET.Builder authorization(ProjectGETAuthorization authorization) {
      this.instance.authorization(authorization);
      return this;
    }
    
    /**
    * returns a built ProjectGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProjectGET build() {
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
  public static ProjectGET.Builder builder() {
    return new ProjectGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProjectGET.Builder toBuilder() {
    ProjectGET.Builder builder = new ProjectGET.Builder();
    return builder.copyOf(this);
  }

}

