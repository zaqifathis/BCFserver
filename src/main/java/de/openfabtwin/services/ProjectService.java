package de.openfabtwin.services;

import de.openfabtwin.generated.dto.ProjectPUT;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EntityResolver entityResolver;

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectEntity getProject(String guid) {
        return entityResolver.resolveProject(guid);
    }

    public ProjectEntity update(String guid, ProjectPUT dto) {
        ProjectEntity project = entityResolver.resolveProject(guid);
        project.setName(dto.getName());
        return projectRepository.save(project);
    }

    public ExtensionEntity getProjectExtension(String guid) {
        return entityResolver.resolveProjectExtension(guid);
    }

}
