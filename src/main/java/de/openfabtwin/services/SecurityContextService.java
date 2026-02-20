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

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityContextService {

    private final ProjectRepository projectRepository;
    private final IdentityProviderService identityProviderService;

    public UserRole getCurrentUserRole() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            List<String> roles = identityProviderService.extractRoles(jwt);
            if (roles.contains("WRITE")) {
                return UserRole.WRITE;
            }
        }
        return UserRole.READ;
    }

    public String getCurrentUserName() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return identityProviderService.extractUsername(jwt);
        }
        return "Anonymous User";
    }

    public String getCurrentUserEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return identityProviderService.extractEmail(jwt);
        }
        return "anonymous";
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

            List<String> userProjectIds = syncAndGetProjectGuids(jwt);
            if (userProjectIds.contains(projectId)) {
                return identityProviderService.getGroupMembers(projectId);
            }
        }
        return List.of();
    }

    private List<String> syncAndGetProjectGuids(Jwt jwt) {
        List<String> projectUuids = identityProviderService.extractProjectIds(jwt);

        for (String uuid : projectUuids) {
            if (!projectRepository.existsByGuid(uuid)) {
                ProjectEntity newProject = new ProjectEntity();
                newProject.setGuid(uuid);
                newProject.setName("Project " + uuid);
                ExtensionEntity ext = new ExtensionEntity();
                ext.setProject(newProject);
                newProject.setExtensions(ext);
                projectRepository.save(newProject);
            }
        }
        return projectUuids;
    }
}
