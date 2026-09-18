package de.openfabtwin.services.security;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

public interface IdentityProviderService {

    List<String> extractRoles(Jwt jwt);

    List<String> extractRoles(Map<String, Object> userAttributes);

    String extractUsername(Jwt jwt);

    String extractEmail(Jwt jwt);
}
