package de.openfabtwin.bcfserver.controllers;

import de.openfabtwin.bcfserver.configs.BcfApiProperties;
import de.openfabtwin.bcfserver.dto.ProjectGET;
import de.openfabtwin.bcfserver.dto.ProjectPUT;
import de.openfabtwin.bcfserver.mappers.ProjectMapper;
import de.openfabtwin.bcfserver.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bcf/{version}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final BcfApiProperties props;

    @ModelAttribute("version")
    public String apiVersion() {
        return props.getVersion(); // "3.0"
    }

    @GetMapping
    public List<ProjectGET> getProjects() {
        return projectService.getAllProjects()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @GetMapping("/{projectId}")
    public ProjectGET getProject(@PathVariable String projectId) throws NotFoundException {
        var project = projectService.getProject(projectId);
        return projectMapper.toDto(project);
    }

    @PutMapping("/{projectId}")
    public ProjectGET updateProject(
            @PathVariable String projectId,
            @RequestBody ProjectPUT dto
    ) throws NotFoundException {
        var updated = projectService.update(projectId, dto);
        return projectMapper.toDto(updated);
    }
}
