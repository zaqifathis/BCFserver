package de.openfabtwin.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * SnapshotPOST
 */

@JsonTypeName("snapshot_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class SnapshotPOST {

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

  private @Nullable String snapshotData;

  public SnapshotPOST snapshotType(@Nullable SnapshotTypeEnum snapshotType) {
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

  public SnapshotPOST snapshotData(@Nullable String snapshotData) {
    this.snapshotData = snapshotData;
    return this;
  }

  /**
   * Get snapshotData
   * @return snapshotData
   */
  
  @Schema(name = "snapshot_data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("snapshot_data")
  public @Nullable String getSnapshotData() {
    return snapshotData;
  }

  public void setSnapshotData(@Nullable String snapshotData) {
    this.snapshotData = snapshotData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SnapshotPOST snapshotPOST = (SnapshotPOST) o;
    return Objects.equals(this.snapshotType, snapshotPOST.snapshotType) &&
        Objects.equals(this.snapshotData, snapshotPOST.snapshotData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(snapshotType, snapshotData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SnapshotPOST {\n");
    sb.append("    snapshotType: ").append(toIndentedString(snapshotType)).append("\n");
    sb.append("    snapshotData: ").append(toIndentedString(snapshotData)).append("\n");
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

    private SnapshotPOST instance;

    public Builder() {
      this(new SnapshotPOST());
    }

    protected Builder(SnapshotPOST instance) {
      this.instance = instance;
    }

    protected Builder copyOf(SnapshotPOST value) { 
      this.instance.setSnapshotType(value.snapshotType);
      this.instance.setSnapshotData(value.snapshotData);
      return this;
    }

    public SnapshotPOST.Builder snapshotType(SnapshotTypeEnum snapshotType) {
      this.instance.snapshotType(snapshotType);
      return this;
    }
    
    public SnapshotPOST.Builder snapshotData(String snapshotData) {
      this.instance.snapshotData(snapshotData);
      return this;
    }
    
    /**
    * returns a built SnapshotPOST instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public SnapshotPOST build() {
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
  public static SnapshotPOST.Builder builder() {
    return new SnapshotPOST.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public SnapshotPOST.Builder toBuilder() {
    SnapshotPOST.Builder builder = new SnapshotPOST.Builder();
    return builder.copyOf(this);
  }

}

