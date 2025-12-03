package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Component
 */

@JsonTypeName("component")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Component {

  private JsonNullable<String> ifcGuid = JsonNullable.<String>undefined();

  private JsonNullable<String> originatingSystem = JsonNullable.<String>undefined();

  private JsonNullable<String> authoringToolId = JsonNullable.<String>undefined();

  public Component ifcGuid(String ifcGuid) {
    this.ifcGuid = JsonNullable.of(ifcGuid);
    return this;
  }

  /**
   * Get ifcGuid
   * @return ifcGuid
   */
  
  @Schema(name = "ifc_guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ifc_guid")
  public JsonNullable<String> getIfcGuid() {
    return ifcGuid;
  }

  public void setIfcGuid(JsonNullable<String> ifcGuid) {
    this.ifcGuid = ifcGuid;
  }

  public Component originatingSystem(String originatingSystem) {
    this.originatingSystem = JsonNullable.of(originatingSystem);
    return this;
  }

  /**
   * Get originatingSystem
   * @return originatingSystem
   */
  
  @Schema(name = "originating_system", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("originating_system")
  public JsonNullable<String> getOriginatingSystem() {
    return originatingSystem;
  }

  public void setOriginatingSystem(JsonNullable<String> originatingSystem) {
    this.originatingSystem = originatingSystem;
  }

  public Component authoringToolId(String authoringToolId) {
    this.authoringToolId = JsonNullable.of(authoringToolId);
    return this;
  }

  /**
   * Get authoringToolId
   * @return authoringToolId
   */
  
  @Schema(name = "authoring_tool_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authoring_tool_id")
  public JsonNullable<String> getAuthoringToolId() {
    return authoringToolId;
  }

  public void setAuthoringToolId(JsonNullable<String> authoringToolId) {
    this.authoringToolId = authoringToolId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Component component = (Component) o;
    return equalsNullable(this.ifcGuid, component.ifcGuid) &&
        equalsNullable(this.originatingSystem, component.originatingSystem) &&
        equalsNullable(this.authoringToolId, component.authoringToolId);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(ifcGuid), hashCodeNullable(originatingSystem), hashCodeNullable(authoringToolId));
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
    sb.append("class Component {\n");
    sb.append("    ifcGuid: ").append(toIndentedString(ifcGuid)).append("\n");
    sb.append("    originatingSystem: ").append(toIndentedString(originatingSystem)).append("\n");
    sb.append("    authoringToolId: ").append(toIndentedString(authoringToolId)).append("\n");
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

    private Component instance;

    public Builder() {
      this(new Component());
    }

    protected Builder(Component instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Component value) { 
      this.instance.setIfcGuid(value.ifcGuid);
      this.instance.setOriginatingSystem(value.originatingSystem);
      this.instance.setAuthoringToolId(value.authoringToolId);
      return this;
    }

    public Component.Builder ifcGuid(String ifcGuid) {
      this.instance.ifcGuid(ifcGuid);
      return this;
    }
    
    public Component.Builder ifcGuid(JsonNullable<String> ifcGuid) {
      this.instance.ifcGuid = ifcGuid;
      return this;
    }
    
    public Component.Builder originatingSystem(String originatingSystem) {
      this.instance.originatingSystem(originatingSystem);
      return this;
    }
    
    public Component.Builder originatingSystem(JsonNullable<String> originatingSystem) {
      this.instance.originatingSystem = originatingSystem;
      return this;
    }
    
    public Component.Builder authoringToolId(String authoringToolId) {
      this.instance.authoringToolId(authoringToolId);
      return this;
    }
    
    public Component.Builder authoringToolId(JsonNullable<String> authoringToolId) {
      this.instance.authoringToolId = authoringToolId;
      return this;
    }
    
    /**
    * returns a built Component instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Component build() {
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
  public static Component.Builder builder() {
    return new Component.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Component.Builder toBuilder() {
    Component.Builder builder = new Component.Builder();
    return builder.copyOf(this);
  }

}

