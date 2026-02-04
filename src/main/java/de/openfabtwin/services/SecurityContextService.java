package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SecurityContextService {

    private final UserService userService;

    public UserRole getCurrentUserRole() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            userService.syncUserWithDatabase(jwt);

            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                Collection<String> roles = (Collection<String>) realmAccess.get("roles");
                if (roles.contains("ADMIN")) {
                    return UserRole.ADMIN;
                }
            }
        }
        return UserRole.USER;
    }

    public String getCurrentUserEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            userService.syncUserWithDatabase(jwt);
            return jwt.getClaim("email");
        }
        return "anonymous@localhost";
    }
}
