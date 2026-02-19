package de.openfabtwin.generated.foundation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserGET {
    @JsonProperty("id")
    @NotBlank
    private String id;

    @JsonProperty("name")
    private String name;

}
