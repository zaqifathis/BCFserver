package de.openfabtwin.services;

import de.openfabtwin.dto.ProjectPUT;
import de.openfabtwin.dto.ProjectPOST;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.ResourceNotFoundException;
import de.openfabtwin.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

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
