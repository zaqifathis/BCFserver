package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * EventAction
 */

@JsonTypeName("event_action")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class EventAction {

  private String type;

  private @Nullable String value = null;

  public EventAction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public EventAction(String type) {
    this.type = type;
  }

  public EventAction type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EventAction value(@Nullable String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public @Nullable String getValue() {
    return value;
  }

  public void setValue(@Nullable String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventAction eventAction = (EventAction) o;
    return Objects.equals(this.type, eventAction.type) &&
        Objects.equals(this.value, eventAction.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventAction {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

    private EventAction instance;

    public Builder() {
      this(new EventAction());
    }

    protected Builder(EventAction instance) {
      this.instance = instance;
    }

    protected Builder copyOf(EventAction value) { 
      this.instance.setType(value.type);
      this.instance.setValue(value.value);
      return this;
    }

    public EventAction.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public EventAction.Builder value(String value) {
      this.instance.value(value);
      return this;
    }
    
    /**
    * returns a built EventAction instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public EventAction build() {
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
  public static EventAction.Builder builder() {
    return new EventAction.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public EventAction.Builder toBuilder() {
    EventAction.Builder builder = new EventAction.Builder();
    return builder.copyOf(this);
  }

}

