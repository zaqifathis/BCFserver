package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.generated.api.ProjectApi;
import de.openfabtwin.services.AuthorizationAssembler;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.utils.BcfProperties;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGET;
import de.openfabtwin.generated.dto.ProjectPUT;
import de.openfabtwin.mappers.ProjectMapper;
import de.openfabtwin.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final AuthorizationAssembler authorizationAssembler;
    private final BcfProperties props;

    @Override
    public ResponseEntity<List<ProjectGET>> getAllProjects(String version) {
        props.validateVersion(version);
        List<String> userProjectsGuids = securityContextService.getUserProjectGuids();
        UserRole role = securityContextService.getCurrentUserRole();
        List<ProjectGET> projects = projectService.getAllProjects(userProjectsGuids)
                .stream()
                .map(project -> {
                    ProjectGET dto = projectMapper.toDto(project);
                    dto.setAuthorization(authorizationAssembler.projectAuthorization(role));
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<ProjectGET> getProjectById(String version, String projectId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        var project = projectService.getProject(projectId);
        ProjectGET dto = projectMapper.toDto(project);
        dto.setAuthorization(authorizationAssembler.projectAuthorization(role));
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ExtensionsGET> getProjectExtension(String version, String projectId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        var ext = projectService.getProjectExtension(projectId);
        ExtensionsGET dto = projectMapper.toExtensionDto(ext);
        dto = authorizationAssembler.applyAuthorization(dto, role);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ProjectGET> updateProjectById(String version, String projectId, ProjectPUT projectPUT) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Project.UPDATE);
        var updated = projectService.update(projectId, projectPUT);
        ProjectGET dto = projectMapper.toDto(updated);
        dto.setAuthorization(authorizationAssembler.projectAuthorization(role));
        return ResponseEntity.ok(dto);
    }

}
