package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.services.security.IdentityProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SecurityContextService {

    private final ProjectRepository projectRepository;
    private final IdentityProviderService identityProviderService;

    public UserRole getCurrentUserRole() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                Collection<String> roles = (Collection<String>) realmAccess.get("roles");
                if (roles.contains("WRITE")) {
                    return UserRole.WRITE;
                }
            }
        }
        return UserRole.READ;
    }

    public String getCurrentUserName() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String name = jwt.getClaim("name");
            return (name != null) ? name : jwt.getClaim("email");
        }
        return "Anonymous User";
    }

    public String getCurrentUserEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("email");
        }
        return "anonymous@localhost";
    }

    public List<String> getUserProjectGuids() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            return syncAndGetProjectGuids(jwt);
        }
        return List.of();
    }

    public boolean hasProjectAccess(String projectId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            List<String> userProjectGuids = syncAndGetProjectGuids(jwt);
            return userProjectGuids.contains(projectId);
        }
        return false;
    }

    public List<String> getUsersOnProject(String projectId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            List<String> userProjectGuids = jwt.getClaim("groups");
            if (userProjectGuids != null && userProjectGuids.contains(projectId)) {
                return identityProviderService.getGroupMembers(projectId);
            }
        }
        return List.of();
    }

    private List<String> syncAndGetProjectGuids(Jwt jwt) {
        List<String> userProjectGuids = jwt.getClaim("groups");
        if (userProjectGuids == null) return List.of();
        for (String guid : userProjectGuids) {
            if (!projectRepository.existsByGuid(guid)) {
                ProjectEntity newProject = new ProjectEntity();
                newProject.setGuid(guid);
                newProject.setName("Project_" + guid);

                ExtensionEntity newExtension = new ExtensionEntity();

                newExtension.setProject(newProject);
                newProject.setExtensions(newExtension);

                projectRepository.save(newProject);
            }
        }
        return userProjectGuids;
    }
}
