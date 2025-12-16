package de.openfabtwin.services;

import de.openfabtwin.generated.dto.ExtensionsGET.ProjectActionsEnum;
import de.openfabtwin.generated.dto.ExtensionsGET.CommentActionsEnum;
import de.openfabtwin.generated.dto.ExtensionsGET.TopicActionsEnum;
import de.openfabtwin.generated.dto.ProjectGETAuthorization;
import de.openfabtwin.generated.dto.ProjectPUT;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ExtensionRepository extensionRepository;

    public List<ProjectEntity> getAllProjects() {
        // TODO:check project access
        return projectRepository.findAll();
    }

    public ProjectEntity getProject(String guid) {
        // TODO:check project access
        return projectRepository.findByGuid(guid)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public ProjectEntity update(String guid, ProjectPUT dto) {
        // TODO:check project access
        ProjectEntity project = projectRepository.findByGuid(guid)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        project.setName(dto.getName());
        return projectRepository.save(project);
    }

    public ExtensionEntity getProjectExtension(String guid) {
        // TODO:check project access

        return extensionRepository.findByProject_Guid(guid)
                .orElseThrow(() -> new EntityNotFoundException("Extension not found"));
    }

    public static List<ProjectGETAuthorization.ProjectActionsEnum> getAuthorizedProjectActions(String roles) {
        List<ProjectGETAuthorization.ProjectActionsEnum> actions = new ArrayList<>();
        if (roles.equals("admin")) {
            actions.add(ProjectGETAuthorization.ProjectActionsEnum.UPDATE);
            actions.add(ProjectGETAuthorization.ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectGETAuthorization.ProjectActionsEnum.CREATE_DOCUMENT);
        } else {
            actions.add(ProjectGETAuthorization.ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectGETAuthorization.ProjectActionsEnum.CREATE_DOCUMENT);
        }
        return actions;
    }

}
