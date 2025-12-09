package de.openfabtwin.services;

import de.openfabtwin.dto.generated.ExtensionsGET;
import de.openfabtwin.dto.generated.ExtensionsGET.CommentActionsEnum;
import de.openfabtwin.dto.generated.ExtensionsGET.TopicActionsEnum;
import de.openfabtwin.dto.generated.ProjectGETAuthorization.ProjectActionsEnum;
import de.openfabtwin.dto.generated.ProjectPUT;
import de.openfabtwin.dto.ProjectPOST;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.ResourceNotFoundException;
import de.openfabtwin.mappers.ProjectMapper;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ExtensionRepository extensionRepository;
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

    public ProjectEntity create(ProjectPOST dto) {
        var project = new ProjectEntity();
        project.setGuid(UUID.randomUUID().toString());
        project.setName(dto.getName());
        project.setAuthor("admin@bcfserver");
        project.setCreatedAt(Instant.now().toString());
        ExtensionEntity ext = createDefaultExtension(project);
        project.setExtensions(ext);;
        return projectRepository.save(project);
    }

    public ExtensionEntity getProjectExtension(String guid) {
        return extensionRepository.findByProject_Guid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Extension not found"));
    }

    // DUMMY DATA
    public static List<ProjectActionsEnum> getAuthorizedProjectActions(String roles) {
        List<ProjectActionsEnum> actions = new ArrayList<>();
        if (roles.equals("admin")) {
            actions.add(ProjectActionsEnum.UPDATE);
            actions.add(ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        } else {
            actions.add(ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        }
        return actions;
    }

    public static List<ExtensionsGET.ProjectActionsEnum> getExtensionProjectActions(String roles) {
        List<ExtensionsGET.ProjectActionsEnum> actions = new ArrayList<>();
        if (roles.equals("admin")) {
            actions.add(ExtensionsGET.ProjectActionsEnum.UPDATE);
            actions.add(ExtensionsGET.ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ExtensionsGET.ProjectActionsEnum.CREATE_DOCUMENT);
        } else {
            actions.add(ExtensionsGET.ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ExtensionsGET.ProjectActionsEnum.CREATE_DOCUMENT);
        }
        return actions;
    }

    // DUMMY DATA
    public static List<TopicActionsEnum> getTopicActions(String roles){
        List<TopicActionsEnum> actions = new ArrayList<>();
        if(roles.equals("admin")) {
            actions.add(TopicActionsEnum.CREATE_COMMENT);
            actions.add(TopicActionsEnum.UPDATE);
            actions.add(TopicActionsEnum.DELETE);
        } else {
            actions.add(TopicActionsEnum.CREATE_COMMENT);
            actions.add(TopicActionsEnum.UPDATE);
        }

        return actions;
    }

    // DUMMY DATA
    public static List<CommentActionsEnum> getCommentActions(String roles){
        List<CommentActionsEnum> actions = new ArrayList<>();
        actions.add(CommentActionsEnum.UPDATE);
        actions.add(CommentActionsEnum.DELETE);
        return actions;
    }

    // DUMMY DATA
    public static ExtensionEntity createDefaultExtension(ProjectEntity project) {
        ExtensionEntity ext = new ExtensionEntity();
        ext.setProject(project);
        ext.setTopicType(new ArrayList<>(Arrays.asList("Issue", "Info", "Request")));
        ext.setTopicStatus(new ArrayList<>(Arrays.asList("Open", "In Progress", "Closed")));
        ext.setTopicLabel(new ArrayList<>(Arrays.asList("Architecture", "Structure", "MEP")));
        ext.setSnippetType(new ArrayList<>(Arrays.asList("Screenshot", "ModelCutout")));
        ext.setPriority(new ArrayList<>(Arrays.asList("Low", "Medium", "High")));
        ext.setUsers(new ArrayList<>(Arrays.asList("admin@bcfserver", "user@bcfserver")));
        ext.setStage(new ArrayList<>(Arrays.asList("Design", "Construction", "Review")));

        return ext;
    }
}
