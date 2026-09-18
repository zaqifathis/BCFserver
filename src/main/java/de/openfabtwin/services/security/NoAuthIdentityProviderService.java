package de.openfabtwin.services.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "auth.enabled", havingValue = "false", matchIfMissing = true)
public class NoAuthIdentityProviderService implements IdentityProviderService {

    @Override
    public List<String> extractRoles(Jwt jwt) {
        return List.of("WRITE");
    }

    @Override
    public List<String> extractRoles(Map<String, Object> userAttributes) {
        return List.of("WRITE");
    }

    @Override
    public String extractUsername(Jwt jwt) {
        return "anonymous";
    }

    @Override
    public String extractEmail(Jwt jwt) {
        return "anonymous";
    }
}
