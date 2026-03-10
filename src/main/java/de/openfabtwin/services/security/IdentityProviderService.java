package de.openfabtwin.services.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface IdentityProviderService {

    List<String> extractRoles(Jwt jwt);

    String extractUsername(Jwt jwt);

    String extractEmail(Jwt jwt);
}
