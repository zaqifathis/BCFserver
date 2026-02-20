package de.openfabtwin.services.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "auth.provider", havingValue = "keycloak")
public class KeycloakProviderService implements IdentityProviderService {

    private final Keycloak keycloak;
    private final String realm;

    public KeycloakProviderService(Keycloak keycloak, @Value("${AUTH_REALM}") String realm) {
        this.keycloak = keycloak;
        this.realm = realm;
    }

    @Override
    public List<String> getGroupMembers(String groupName) {
        List<GroupRepresentation> groups = keycloak.realm(realm)
                .groups()
                .groups(groupName, 0, 1, true);

        if (groups == null || groups.isEmpty()) {
            return List.of();
        }

        String internalId = groups.getFirst().getId();
        return keycloak.realm(realm)
                .groups()
                .group(internalId)
                .members()
                .stream()
                .map(UserRepresentation::getEmail)
                .toList();
    }
}
