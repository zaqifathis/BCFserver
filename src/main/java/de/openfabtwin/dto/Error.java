package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Schema for error, BCF REST API.
 */

@Schema(name = "error", description = "Schema for error, BCF REST API.")
@JsonTypeName("error")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class Error {

  private String message;

  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(String message) {
    this.message = message;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @NotNull 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.message, error.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

    private Error instance;

    public Builder() {
      this(new Error());
    }

    protected Builder(Error instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Error value) { 
      this.instance.setMessage(value.message);
      return this;
    }

    public Error.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built Error instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Error build() {
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
  public static Error.Builder builder() {
    return new Error.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Error.Builder toBuilder() {
    Error.Builder builder = new Error.Builder();
    return builder.copyOf(this);
  }

}

