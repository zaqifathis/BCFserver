package de.openfabtwin.dto.generated;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * SnapshotGET
 */

@JsonTypeName("snapshot_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class SnapshotGET {

  /**
   * Gets or Sets snapshotType
   */
  public enum SnapshotTypeEnum {
    JPG("jpg"),
    
    PNG("png");

    private final String value;

    SnapshotTypeEnum(String value) {
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
    public static SnapshotTypeEnum fromValue(String value) {
      for (SnapshotTypeEnum b : SnapshotTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable SnapshotTypeEnum snapshotType;

  public SnapshotGET snapshotType(@Nullable SnapshotTypeEnum snapshotType) {
    this.snapshotType = snapshotType;
    return this;
  }

  /**
   * Get snapshotType
   * @return snapshotType
   */
  
  @Schema(name = "snapshot_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("snapshot_type")
  public @Nullable SnapshotTypeEnum getSnapshotType() {
    return snapshotType;
  }

  public void setSnapshotType(@Nullable SnapshotTypeEnum snapshotType) {
    this.snapshotType = snapshotType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SnapshotGET snapshotGET = (SnapshotGET) o;
    return Objects.equals(this.snapshotType, snapshotGET.snapshotType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(snapshotType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SnapshotGET {\n");
    sb.append("    snapshotType: ").append(toIndentedString(snapshotType)).append("\n");
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

    private SnapshotGET instance;

    public Builder() {
      this(new SnapshotGET());
    }

    protected Builder(SnapshotGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(SnapshotGET value) { 
      this.instance.setSnapshotType(value.snapshotType);
      return this;
    }

    public SnapshotGET.Builder snapshotType(SnapshotTypeEnum snapshotType) {
      this.instance.snapshotType(snapshotType);
      return this;
    }
    
    /**
    * returns a built SnapshotGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public SnapshotGET build() {
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
  public static SnapshotGET.Builder builder() {
    return new SnapshotGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public SnapshotGET.Builder toBuilder() {
    SnapshotGET.Builder builder = new SnapshotGET.Builder();
    return builder.copyOf(this);
  }

}

