package de.openfabtwin.controllers;

import de.openfabtwin.api.generated.ProjectApi;
import de.openfabtwin.utils.BcfProperties;
import de.openfabtwin.dto.generated.ExtensionsGET;
import de.openfabtwin.dto.generated.ProjectGET;
import de.openfabtwin.dto.generated.ProjectPUT;
import de.openfabtwin.mappers.ProjectMapper;
import de.openfabtwin.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final BcfProperties props;

    @Override
    public ResponseEntity<List<ProjectGET>> getAllProjects(String version) {
        props.validateVersion(version);
        List<ProjectGET> projects = projectService.getAllProjects()
                .stream()
                .map(projectMapper::toDto)
                .toList();
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<ProjectGET> getProjectById(String version, String projectId) {
        props.validateVersion(version);
        var project = projectService.getProject(projectId);
        return ResponseEntity.ok(projectMapper.toDto(project));
    }

    @Override
    public ResponseEntity<ExtensionsGET> getProjectExtension(String version, String projectId) {
        props.validateVersion(version);
        var ext = projectService.getProjectExtension(projectId);
        return ResponseEntity.ok(projectMapper.toExtensionDto(ext));
    }

    @Override
    public ResponseEntity<ProjectGET> updateProjectById(String version, String projectId, ProjectPUT projectPUT) {
        props.validateVersion(version);
        var updated = projectService.update(projectId, projectPUT);
        return ResponseEntity.ok(projectMapper.toDto(updated));
    }

}
