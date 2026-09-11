package de.openfabtwin.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OidcDiscoveryService {

    @Value("${AUTH_SERVER_URL}")
    private String externalUrl;

    @Value("${AUTH_SERVER_INTERNAL_URL:${AUTH_SERVER_URL}}")
    private String internalUrl;

    @Value("${AUTH_REALM}")
    private String realm;

    private final RestTemplate restTemplate = new RestTemplate();

    private Map<String, Object> cachedConfig;

    public Map<String, Object> getOpenIdConfiguration() {

        if (cachedConfig != null) {
            return cachedConfig; // simple caching
        }

        String discoveryUrl = internalUrl + "/realms/" + realm + "/.well-known/openid-configuration";
        Map<String, Object> config = restTemplate.getForObject(discoveryUrl, Map.class);

        // Endpoints are handed to external BCF clients, so they must never point at the Docker-internal host
        config.replaceAll((key, value) ->
                value instanceof String s && s.startsWith(internalUrl)
                        ? externalUrl + s.substring(internalUrl.length())
                        : value);

        cachedConfig = config;
        return cachedConfig;
    }
}
