package de.openfabtwin.services.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(name = "auth.enabled", havingValue = "true")
public class StandardOidcProviderService implements IdentityProviderService {

    private final String rolesClaim;

    public StandardOidcProviderService(@Value("${auth.roles-claim:roles}") String rolesClaim) {
        this.rolesClaim = rolesClaim;
    }

    @Override
    public List<String> extractRoles(Jwt jwt) {
        return extractRolesFromMap(jwt.getClaims());
    }

    @Override
    public List<String> extractRoles(Map<String, Object> userAttributes) {
        return extractRolesFromMap(userAttributes);
    }

    private List<String> extractRolesFromMap(Map<String, Object> map) {
        String[] parts = rolesClaim.split("\\.");
        Object current = map;
        for (String part : parts) {
            if (current instanceof Map<?, ?> m) current = m.get(part);
            else return List.of();
        }
        return current instanceof List<?> list ? (List<String>) list : List.of();
    }

    @Override
    public String extractUsername(Jwt jwt) {
        String name = jwt.getClaim("name");
        return name != null ? name : jwt.getClaim("email");
    }

    @Override
    public String extractEmail(Jwt jwt) {
        return jwt.getClaim("email");
    }
}
