package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * BitmapGET
 */

@JsonTypeName("bitmap_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class BitmapGET {

  private @Nullable String guid;

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

  private @Nullable Location location = null;

  private @Nullable Direction normal;

  private @Nullable Direction up;

  private @Nullable BigDecimal height;

  public BitmapGET guid(@Nullable String guid) {
    this.guid = guid;
    return this;
  }

  /**
   * Get guid
   * @return guid
   */
  
  @Schema(name = "guid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("guid")
  public @Nullable String getGuid() {
    return guid;
  }

  public void setGuid(@Nullable String guid) {
    this.guid = guid;
  }

  public BitmapGET bitmapType(@Nullable BitmapTypeEnum bitmapType) {
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

  public BitmapGET location(@Nullable Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @Valid 
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public @Nullable Location getLocation() {
    return location;
  }

  public void setLocation(@Nullable Location location) {
    this.location = location;
  }

  public BitmapGET normal(@Nullable Direction normal) {
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

  public BitmapGET up(@Nullable Direction up) {
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

  public BitmapGET height(@Nullable BigDecimal height) {
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
    BitmapGET bitmapGET = (BitmapGET) o;
    return Objects.equals(this.guid, bitmapGET.guid) &&
        Objects.equals(this.bitmapType, bitmapGET.bitmapType) &&
        Objects.equals(this.location, bitmapGET.location) &&
        Objects.equals(this.normal, bitmapGET.normal) &&
        Objects.equals(this.up, bitmapGET.up) &&
        Objects.equals(this.height, bitmapGET.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guid, bitmapType, location, normal, up, height);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BitmapGET {\n");
    sb.append("    guid: ").append(toIndentedString(guid)).append("\n");
    sb.append("    bitmapType: ").append(toIndentedString(bitmapType)).append("\n");
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

    private BitmapGET instance;

    public Builder() {
      this(new BitmapGET());
    }

    protected Builder(BitmapGET instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BitmapGET value) { 
      this.instance.setGuid(value.guid);
      this.instance.setBitmapType(value.bitmapType);
      this.instance.setLocation(value.location);
      this.instance.setNormal(value.normal);
      this.instance.setUp(value.up);
      this.instance.setHeight(value.height);
      return this;
    }

    public BitmapGET.Builder guid(String guid) {
      this.instance.guid(guid);
      return this;
    }
    
    public BitmapGET.Builder bitmapType(BitmapTypeEnum bitmapType) {
      this.instance.bitmapType(bitmapType);
      return this;
    }
    
    public BitmapGET.Builder location(Location location) {
      this.instance.location(location);
      return this;
    }
    
    public BitmapGET.Builder normal(Direction normal) {
      this.instance.normal(normal);
      return this;
    }
    
    public BitmapGET.Builder up(Direction up) {
      this.instance.up(up);
      return this;
    }
    
    public BitmapGET.Builder height(BigDecimal height) {
      this.instance.height(height);
      return this;
    }
    
    /**
    * returns a built BitmapGET instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BitmapGET build() {
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
  public static BitmapGET.Builder builder() {
    return new BitmapGET.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BitmapGET.Builder toBuilder() {
    BitmapGET.Builder builder = new BitmapGET.Builder();
    return builder.copyOf(this);
  }

}

