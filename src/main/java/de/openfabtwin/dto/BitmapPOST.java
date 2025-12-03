package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * BitmapPOST
 */

@JsonTypeName("bitmap_POST")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T12:04:39.178099100+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class BitmapPOST {

  /**
   * Gets or Sets bitmapType
   */
  public enum BitmapTypeEnum {
    JPG("jpg"),
    
    PNG("png");

    private final String value;

    BitmapTypeEnum(String value) {
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
    public static BitmapTypeEnum fromValue(String value) {
      for (BitmapTypeEnum b : BitmapTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable BitmapTypeEnum bitmapType;

  private @Nullable String bitmapData;

  private JsonNullable<Location> location = JsonNullable.<Location>undefined();

  private @Nullable Direction normal;

  private @Nullable Direction up;

  private @Nullable BigDecimal height;

  public BitmapPOST bitmapType(@Nullable BitmapTypeEnum bitmapType) {
    this.bitmapType = bitmapType;
    return this;
  }

  /**
   * Get bitmapType
   * @return bitmapType
   */
  
  @Schema(name = "bitmap_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bitmap_type")
  public @Nullable BitmapTypeEnum getBitmapType() {
    return bitmapType;
  }

  public void setBitmapType(@Nullable BitmapTypeEnum bitmapType) {
    this.bitmapType = bitmapType;
  }

  public BitmapPOST bitmapData(@Nullable String bitmapData) {
    this.bitmapData = bitmapData;
    return this;
  }

  /**
   * Get bitmapData
   * @return bitmapData
   */
  
  @Schema(name = "bitmap_data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bitmap_data")
  public @Nullable String getBitmapData() {
    return bitmapData;
  }

  public void setBitmapData(@Nullable String bitmapData) {
    this.bitmapData = bitmapData;
  }

  public BitmapPOST location(Location location) {
    this.location = JsonNullable.of(location);
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @Valid 
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public JsonNullable<Location> getLocation() {
    return location;
  }

  public void setLocation(JsonNullable<Location> location) {
    this.location = location;
  }

  public BitmapPOST normal(@Nullable Direction normal) {
    this.normal = normal;
    return this;
  }

  /**
   * Get normal
   * @return normal
   */
  @Valid 
  @Schema(name = "normal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("normal")
  public @Nullable Direction getNormal() {
    return normal;
  }

  public void setNormal(@Nullable Direction normal) {
    this.normal = normal;
  }

  public BitmapPOST up(@Nullable Direction up) {
    this.up = up;
    return this;
  }

  /**
   * Get up
   * @return up
   */
  @Valid 
  @Schema(name = "up", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("up")
  public @Nullable Direction getUp() {
    return up;
  }

  public void setUp(@Nullable Direction up) {
    this.up = up;
  }

  public BitmapPOST height(@Nullable BigDecimal height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
   */
  @Valid 
  @Schema(name = "height", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("height")
  public @Nullable BigDecimal getHeight() {
    return height;
  }

  public void setHeight(@Nullable BigDecimal height) {
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BitmapPOST bitmapPOST = (BitmapPOST) o;
    return Objects.equals(this.bitmapType, bitmapPOST.bitmapType) &&
        Objects.equals(this.bitmapData, bitmapPOST.bitmapData) &&
        equalsNullable(this.location, bitmapPOST.location) &&
        Objects.equals(this.normal, bitmapPOST.normal) &&
        Objects.equals(this.up, bitmapPOST.up) &&
        Objects.equals(this.height, bitmapPOST.height);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(bitmapType, bitmapData, hashCodeNullable(location), normal, up, height);
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
    sb.append("class BitmapPOST {\n");
    sb.append("    bitmapType: ").append(toIndentedString(bitmapType)).append("\n");
    sb.append("    bitmapData: ").append(toIndentedString(bitmapData)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    normal: ").append(toIndentedString(normal)).append("\n");
    sb.append("    up: ").append(toIndentedString(up)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
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

    private BitmapPOST instance;

    public Builder() {
      this(new BitmapPOST());
    }

    protected Builder(BitmapPOST instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BitmapPOST value) { 
      this.instance.setBitmapType(value.bitmapType);
      this.instance.setBitmapData(value.bitmapData);
      this.instance.setLocation(value.location);
      this.instance.setNormal(value.normal);
      this.instance.setUp(value.up);
      this.instance.setHeight(value.height);
      return this;
    }

    public BitmapPOST.Builder bitmapType(BitmapTypeEnum bitmapType) {
      this.instance.bitmapType(bitmapType);
      return this;
    }
    
    public BitmapPOST.Builder bitmapData(String bitmapData) {
      this.instance.bitmapData(bitmapData);
      return this;
    }
    
    public BitmapPOST.Builder location(Location location) {
      this.instance.location(location);
      return this;
    }
    
    public BitmapPOST.Builder location(JsonNullable<Location> location) {
      this.instance.location = location;
      return this;
    }
    
    public BitmapPOST.Builder normal(Direction normal) {
      this.instance.normal(normal);
      return this;
    }
    
    public BitmapPOST.Builder up(Direction up) {
      this.instance.up(up);
      return this;
    }
    
    public BitmapPOST.Builder height(BigDecimal height) {
      this.instance.height(height);
      return this;
    }
    
    /**
    * returns a built BitmapPOST instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BitmapPOST build() {
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
  public static BitmapPOST.Builder builder() {
    return new BitmapPOST.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BitmapPOST.Builder toBuilder() {
    BitmapPOST.Builder builder = new BitmapPOST.Builder();
    return builder.copyOf(this);
  }

}

