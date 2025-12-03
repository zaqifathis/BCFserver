package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for project PUT, BCF REST API.
 */

@Schema(name = "project_PUT", description = "Schema for project PUT, BCF REST API.")
@JsonTypeName("project_PUT")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ProjectPUT {

  private String name;

  public ProjectPUT() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProjectPUT(String name) {
    this.name = name;
  }

  public ProjectPUT name(String name) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectPUT projectPUT = (ProjectPUT) o;
    return Objects.equals(this.name, projectPUT.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectPUT {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

    private ProjectPUT instance;

    public Builder() {
      this(new ProjectPUT());
    }

    protected Builder(ProjectPUT instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProjectPUT value) { 
      this.instance.setName(value.name);
      return this;
    }

    public ProjectPUT.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    /**
    * returns a built ProjectPUT instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProjectPUT build() {
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
  public static ProjectPUT.Builder builder() {
    return new ProjectPUT.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProjectPUT.Builder toBuilder() {
    ProjectPUT.Builder builder = new ProjectPUT.Builder();
    return builder.copyOf(this);
  }

}

