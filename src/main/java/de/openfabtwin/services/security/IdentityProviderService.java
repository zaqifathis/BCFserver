package de.openfabtwin.services.security;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

public interface IdentityProviderService {

    List<String> extractRoles(Jwt jwt);

    List<String> extractRoles(Map<String, Object> userAttributes);

    // Roles granted to requests without an authenticated principal
    List<String> anonymousRoles();

    String extractUsername(Jwt jwt);

    String extractEmail(Jwt jwt);
}
