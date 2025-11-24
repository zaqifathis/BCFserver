package de.openfabtwin.bcfserver.services;

import de.openfabtwin.bcfserver.dto.ProjectPUT;
import de.openfabtwin.bcfserver.entities.ProjectEntity;
import de.openfabtwin.bcfserver.mappers.ProjectMapper;
import de.openfabtwin.bcfserver.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public ProjectEntity getProject(String guid) throws NotFoundException {
        return projectRepository.findByGuid(guid)
                .orElseThrow(NotFoundException::new);
    }

    public ProjectEntity create(ProjectPUT dto) {
        ProjectEntity entity = projectMapper.toEntity(dto);
        entity.setGuid(UUID.randomUUID().toString());
        entity.setCreatedAt(OffsetDateTime.now().toString());
        return projectRepository.save(entity);
    }

    public ProjectEntity update(String guid, ProjectPUT dto) throws NotFoundException {
        ProjectEntity project = projectRepository.findByGuid(guid)
                .orElseThrow(NotFoundException::new);

        project.setName(dto.getName());
        return projectRepository.save(project);
    }

}
