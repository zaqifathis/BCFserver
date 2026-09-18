package de.openfabtwin.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OidcDiscoveryService {

    @Value("${auth.discovery-uri:}")
    private String discoveryUri;

    @Value("${auth.enabled:false}")
    private boolean authEnabled;

    private final RestTemplate restTemplate = new RestTemplate();
    private Map<String, Object> cachedConfig;

    public Map<String, Object> getOpenIdConfiguration() {
        if (!authEnabled || discoveryUri.isBlank()) return Map.of();
        if (cachedConfig != null) return cachedConfig;
        cachedConfig = restTemplate.getForObject(discoveryUri, Map.class);
        return cachedConfig;
    }
}
