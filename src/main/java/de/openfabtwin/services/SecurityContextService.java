package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextService {

    public UserRole getCurrentUserRole() {
        // Temporary implementation
        // will replace with OAuth2
        return UserRole.ADMIN;
    }
}
