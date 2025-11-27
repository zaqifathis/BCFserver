package de.openfabtwin.bcfserver.services;

import de.openfabtwin.bcfserver.dto.ProjectPUT;
import de.openfabtwin.bcfserver.dtos.ProjectPOST;
import de.openfabtwin.bcfserver.entities.ProjectEntity;
import de.openfabtwin.bcfserver.exceptions.ResourceNotFoundException;
import de.openfabtwin.bcfserver.mappers.ProjectMapper;
import de.openfabtwin.bcfserver.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectEntity getProject(String guid) {
        return projectRepository.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    public ProjectEntity update(String guid, ProjectPUT dto) {
        ProjectEntity project = projectRepository.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setName(dto.getName());
        return projectRepository.save(project);
    }

    public ProjectEntity create(ProjectPOST dto) { //TODO: connection to user management
        var entity = new ProjectEntity();
        entity.setGuid(UUID.randomUUID().toString());
        entity.setName(dto.getName());
        entity.setAuthor("admin");
        entity.setCreatedAt(Instant.now().toString());
        return projectRepository.save(entity);
    }
}
