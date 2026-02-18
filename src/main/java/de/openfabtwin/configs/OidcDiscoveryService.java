package de.openfabtwin.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OidcDiscoveryService {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private final RestTemplate restTemplate = new RestTemplate();

    private Map<String, Object> cachedConfig;

    public Map<String, Object> getOpenIdConfiguration() {

        if (cachedConfig != null) {
            return cachedConfig; // simple caching
        }

        String discoveryUrl = issuerUri + "/.well-known/openid-configuration";
        cachedConfig = restTemplate.getForObject(discoveryUrl, Map.class);

        return cachedConfig;
    }
}
