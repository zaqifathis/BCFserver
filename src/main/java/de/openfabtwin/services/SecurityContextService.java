package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.services.security.IdentityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SecurityContextService {

    private final ProjectRepository projectRepository;
    private final IdentityProviderService identityProviderService;

    public UserRole getCurrentUserRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            List<String> roles = identityProviderService.extractRoles(jwt);
            if (roles.contains("WRITE")) {
                return UserRole.WRITE;
            }
        }
        return UserRole.READ;
    }

    public String getCurrentUserName() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            return identityProviderService.extractUsername(jwt);
        }
        return "Anonymous User";
    }

    public String getCurrentUserEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            return identityProviderService.extractEmail(jwt);
        }
        return "anonymous";
    }

    public List<String> getUserProjectGuids() {
        String email = getCurrentUserEmail();

        List<String> privateGuids = projectRepository.findProjectGuidsByUserEmail(email);
        List<String> publicGuids = projectRepository.findPublicProjectGuids();

        List<String> result = new ArrayList<>(publicGuids);
        for (String guid : privateGuids) {
            if (!result.contains(guid)) {
                result.add(guid);
            }
        }
        return result;
    }

    public boolean hasProjectAccess(String projectId) {
        return getUserProjectGuids().contains(projectId);
    }

}
