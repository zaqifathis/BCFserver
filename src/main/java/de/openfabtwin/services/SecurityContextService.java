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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        syncProjects();
        return getProjectIdsFromPrincipal();
    }

    public boolean hasProjectAccess(String projectId) {
        return getUserProjectGuids().contains(projectId);
    }

    public List<String> getUsersOnProject(String projectId) {
        List<String> userProjectIds = getUserProjectGuids();
        if (userProjectIds.contains(projectId)) {
            return identityProviderService.getGroupMembers(projectId);
        }
        return Collections.emptyList();
    }

    private List<String> getProjectIdsFromPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            return Collections.emptyList();
        }
        if (auth.getPrincipal() instanceof Jwt jwt) {
            return identityProviderService.extractProjectIds(jwt);
        } else if (auth.getPrincipal() instanceof OAuth2User oauth2User) {
            return oauth2User.getAttribute("groups");
        }
        return Collections.emptyList();
    }

    private void syncProjects() {
        List<String> keycloakGuids = identityProviderService.getAllGroupNames();
        if (keycloakGuids.isEmpty()) return;

        Set<String> localGuidsSet = new HashSet<>(projectRepository.findAllGuids());
        List<ProjectEntity> toAdd = keycloakGuids.stream()
                .filter(id -> !localGuidsSet.contains(id))
                .map(id -> {
                    ProjectEntity p = new ProjectEntity();
                    p.setGuid(id);
                    p.setName("Project " + id);
                    ExtensionEntity ext = new ExtensionEntity();
                    ext.setProject(p);
                    p.setExtensions(ext);
                    return p;
                }).toList();

        if (!toAdd.isEmpty()) projectRepository.saveAll(toAdd);

        Set<String> keycloakGuidsSet = new HashSet<>(keycloakGuids);
        List<String> toDelete = localGuidsSet.stream()
                .filter(id -> !keycloakGuidsSet.contains(id))
                .toList();

        if (!toDelete.isEmpty()) {
            projectRepository.deleteByGuidIn(toDelete);
        }
    }
}
