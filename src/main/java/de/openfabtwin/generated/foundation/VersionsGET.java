package de.openfabtwin.generated.foundation;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.openfabtwin.generated.foundation.VersionsGETVersionsInner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * VersionsGET
 */

@JsonTypeName("Versions_GET")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-02T13:20:11.030282800+01:00[Europe/Berlin]")
public class VersionsGET {

  @Valid
  private List<@Valid VersionsGETVersionsInner> versions = new ArrayList<>();

  public VersionsGET() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VersionsGET(List<@Valid VersionsGETVersionsInner> versions) {
    this.versions = versions;
  }

  public VersionsGET versions(List<@Valid VersionsGETVersionsInner> versions) {
    this.versions = versions;
    return this;
  }

  public VersionsGET addVersionsItem(VersionsGETVersionsInner versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<>();
    }
    this.versions.add(versionsItem);
    return this;
  }

  /**
   * Get versions
   * @return versions
  */
  @NotNull @Valid 
  @Schema(name = "versions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("versions")
  public List<@Valid VersionsGETVersionsInner> getVersions() {
    return versions;
  }

  public void setVersions(List<@Valid VersionsGETVersionsInner> versions) {
    this.versions = versions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionsGET versionsGET = (VersionsGET) o;
    return Objects.equals(this.versions, versionsGET.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionsGET {\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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

