package de.openfabtwin.bcfserver.controllers;

import de.openfabtwin.bcfserver.api.ProjectApi;
import de.openfabtwin.bcfserver.configs.BcfApiProperties;
import de.openfabtwin.bcfserver.dto.ExtensionsGET;
import de.openfabtwin.bcfserver.dto.ProjectGET;
import de.openfabtwin.bcfserver.dto.ProjectGETAuthorization;
import de.openfabtwin.bcfserver.dto.ProjectPUT;
import de.openfabtwin.bcfserver.dtos.ProjectPOST;
import de.openfabtwin.bcfserver.mappers.ProjectMapper;
import de.openfabtwin.bcfserver.services.AuthorizationService;
import de.openfabtwin.bcfserver.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final BcfApiProperties props;

    private void validateVersion(String version) {
        if (!version.equals(props.getVersion())) {
            throw new IllegalArgumentException("Unsupported API version " + version);
        }
    }

    @PostMapping("/bcf/{version}/projects")
    public ResponseEntity<ProjectGET> createProject(@PathVariable String version, @RequestBody ProjectPOST dto) {
        validateVersion(version);
        var created = projectService.create(dto);
        var dtoOut  = projectMapper.toDto(created);
        var auth = new ProjectGETAuthorization();
        auth.setProjectActions(AuthorizationService.getProjectActions("admin", created)); //TODO: connection to user management
        dtoOut.setAuthorization(auth);
        return ResponseEntity.status(201).body(dtoOut );
    }

    @Override
    public ResponseEntity<List<ProjectGET>> getAllProjects(String version) {
        validateVersion(version);
        List<ProjectGET> projects = projectService.getAllProjects()
                .stream()
                .map(projectMapper::toDto)
                .toList();
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<ProjectGET> getProjectById(String version, String projectId) {
        validateVersion(version);
        var project = projectService.getProject(projectId);
        return ResponseEntity.ok(projectMapper.toDto(project));
    }

    @Override
    public ResponseEntity<ExtensionsGET> getProjectExtension(String version, String projectId) {
        validateVersion(version);
        // Implementation for getting project extensions goes here
        return ResponseEntity.ok(new ExtensionsGET());
    }

    @Override
    public ResponseEntity<ProjectGET> updateProjectById(String version, String projectId, ProjectPUT projectPUT) {
        validateVersion(version);
        var updated = projectService.update(projectId, projectPUT);
        return ResponseEntity.ok(projectMapper.toDto(updated));
    }

}
