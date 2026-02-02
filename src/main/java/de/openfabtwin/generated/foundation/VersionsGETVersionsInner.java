package de.openfabtwin.generated.foundation;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * VersionsGETVersionsInner
 */

@JsonTypeName("Versions_GET_versions_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-02T13:20:11.030282800+01:00[Europe/Berlin]")
public class VersionsGETVersionsInner {

  private String apiId;

  private String versionId;

  private String detailedVersion;

  private String apiBaseUrl;

  public VersionsGETVersionsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VersionsGETVersionsInner(String apiId, String versionId) {
    this.apiId = apiId;
    this.versionId = versionId;
  }

  public VersionsGETVersionsInner apiId(String apiId) {
    this.apiId = apiId;
    return this;
  }

  /**
   * Get apiId
   * @return apiId
  */
  @NotNull 
  @Schema(name = "api_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("api_id")
  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }

  public VersionsGETVersionsInner versionId(String versionId) {
    this.versionId = versionId;
    return this;
  }

  /**
   * Get versionId
   * @return versionId
  */
  @NotNull 
  @Schema(name = "version_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version_id")
  public String getVersionId() {
    return versionId;
  }

  public void setVersionId(String versionId) {
    this.versionId = versionId;
  }

  public VersionsGETVersionsInner detailedVersion(String detailedVersion) {
    this.detailedVersion = detailedVersion;
    return this;
  }

  /**
   * Get detailedVersion
   * @return detailedVersion
  */
  
  @Schema(name = "detailed_version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detailed_version")
  public String getDetailedVersion() {
    return detailedVersion;
  }

  public void setDetailedVersion(String detailedVersion) {
    this.detailedVersion = detailedVersion;
  }

  public VersionsGETVersionsInner apiBaseUrl(String apiBaseUrl) {
    this.apiBaseUrl = apiBaseUrl;
    return this;
  }

  /**
   * Get apiBaseUrl
   * @return apiBaseUrl
  */
  
  @Schema(name = "api_base_url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("api_base_url")
  public String getApiBaseUrl() {
    return apiBaseUrl;
  }

  public void setApiBaseUrl(String apiBaseUrl) {
    this.apiBaseUrl = apiBaseUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionsGETVersionsInner versionsGETVersionsInner = (VersionsGETVersionsInner) o;
    return Objects.equals(this.apiId, versionsGETVersionsInner.apiId) &&
        Objects.equals(this.versionId, versionsGETVersionsInner.versionId) &&
        Objects.equals(this.detailedVersion, versionsGETVersionsInner.detailedVersion) &&
        Objects.equals(this.apiBaseUrl, versionsGETVersionsInner.apiBaseUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiId, versionId, detailedVersion, apiBaseUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionsGETVersionsInner {\n");
    sb.append("    apiId: ").append(toIndentedString(apiId)).append("\n");
    sb.append("    versionId: ").append(toIndentedString(versionId)).append("\n");
    sb.append("    detailedVersion: ").append(toIndentedString(detailedVersion)).append("\n");
    sb.append("    apiBaseUrl: ").append(toIndentedString(apiBaseUrl)).append("\n");
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
}

