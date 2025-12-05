package de.openfabtwin.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ViewpointGETAuthorization
 */

@JsonTypeName("viewpoint_GET_authorization")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-05T09:23:37.160769500+01:00[Europe/Berlin]", comments = "Generator version: 7.17.0")
public class ViewpointGETAuthorization {

  /**
   * Gets or Sets viewpointActions
   */
  public enum ViewpointActionsEnum {
    DELETE("delete");

    private final String value;

    ViewpointActionsEnum(String value) {
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
    public static ViewpointActionsEnum fromValue(String value) {
      for (ViewpointActionsEnum b : ViewpointActionsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Valid
  private List<ViewpointActionsEnum> viewpointActions = new ArrayList<>();

  public ViewpointGETAuthorization viewpointActions(List<ViewpointActionsEnum> viewpointActions) {
    this.viewpointActions = viewpointActions;
    return this;
  }

  public ViewpointGETAuthorization addViewpointActionsItem(ViewpointActionsEnum viewpointActionsItem) {
    if (this.viewpointActions == null) {
      this.viewpointActions = new ArrayList<>();
    }
    this.viewpointActions.add(viewpointActionsItem);
    return this;
  }

  /**
   * Get viewpointActions
   * @return viewpointActions
   */
  
  @Schema(name = "viewpoint_actions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("viewpoint_actions")
  public List<ViewpointActionsEnum> getViewpointActions() {
    return viewpointActions;
  }

  public void setViewpointActions(List<ViewpointActionsEnum> viewpointActions) {
    this.viewpointActions = viewpointActions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewpointGETAuthorization viewpointGETAuthorization = (ViewpointGETAuthorization) o;
    return Objects.equals(this.viewpointActions, viewpointGETAuthorization.viewpointActions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(viewpointActions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViewpointGETAuthorization {\n");
    sb.append("    viewpointActions: ").append(toIndentedString(viewpointActions)).append("\n");
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

    private ViewpointGETAuthorization instance;

    public Builder() {
      this(new ViewpointGETAuthorization());
    }

    protected Builder(ViewpointGETAuthorization instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ViewpointGETAuthorization value) { 
      this.instance.setViewpointActions(value.viewpointActions);
      return this;
    }

    public ViewpointGETAuthorization.Builder viewpointActions(List<ViewpointActionsEnum> viewpointActions) {
      this.instance.viewpointActions(viewpointActions);
      return this;
    }
    
    /**
    * returns a built ViewpointGETAuthorization instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ViewpointGETAuthorization build() {
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
  public static ViewpointGETAuthorization.Builder builder() {
    return new ViewpointGETAuthorization.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ViewpointGETAuthorization.Builder toBuilder() {
    ViewpointGETAuthorization.Builder builder = new ViewpointGETAuthorization.Builder();
    return builder.copyOf(this);
  }

}

