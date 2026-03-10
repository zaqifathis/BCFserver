package de.openfabtwin.services.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "auth.provider", havingValue = "keycloak")
@Profile("!test")
public class KeycloakProviderService implements IdentityProviderService {

    private final Keycloak keycloak;
    private final String realm;

    public KeycloakProviderService(Keycloak keycloak, @Value("${AUTH_REALM}") String realm) {
        this.keycloak = keycloak;
        this.realm = realm;
    }

    @Override
    public List<String> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            return roles;
        }
        return List.of();
    }

    @Override
    public String extractUsername(Jwt jwt) {
        String name = jwt.getClaim("name");
        return (name != null) ? name : jwt.getClaim("email");
    }

    @Override
    public String extractEmail(Jwt jwt) {
        return jwt.getClaim("email");
    }
}
