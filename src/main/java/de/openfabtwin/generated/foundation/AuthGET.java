package de.openfabtwin.generated.foundation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class AuthGET {
    @JsonProperty("oauth2_auth_url")
    private String oauth2AuthUrl;

    @JsonProperty("oauth2_token_url")
    private String oauth2TokenUrl;

    @JsonProperty("oauth2_dynamic_client_reg_url")
    private String oauth2DynamicClientRegUrl;

    @JsonProperty("http_basic_supported")
    private Boolean httpBasicSupported = false;

    @JsonProperty("supported_oauth2_flows")
    private List<String> supportedOauth2Flows;
}
