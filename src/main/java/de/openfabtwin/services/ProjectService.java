package de.openfabtwin.services;

import de.openfabtwin.dto.ExtensionsGET.CommentActionsEnum;
import de.openfabtwin.dto.ExtensionsGET.TopicActionsEnum;
import de.openfabtwin.dto.ProjectGETAuthorization.ProjectActionsEnum;
import de.openfabtwin.dto.ProjectPUT;
import de.openfabtwin.ProjectPOST;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.ResourceNotFoundException;
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
        var project = new ProjectEntity();
        project.setGuid(UUID.randomUUID().toString());
        project.setName(dto.getName());
        project.setAuthor("default@author");
        project.setCreatedAt(Instant.now().toString());

        ExtensionEntity ext = createDefaultExtension(project);
        project.setExtensions(ext);
        return projectRepository.save(project);
    }

    public ExtensionEntity getProjectExtension(String guid) {
        return extensionRepository.findByProject_Guid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Extension not found"));
    }

    // DUMMY DATA
    public static List<ProjectActionsEnum> getProjectActions() {
        List<ProjectActionsEnum> actions = new ArrayList<>();
        actions.add(ProjectActionsEnum.UPDATE);
        actions.add(ProjectActionsEnum.CREATE_TOPIC);
        actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        return actions;
    }

    // DUMMY DATA
    public static List<TopicActionsEnum> getTopicActions() {
        List<TopicActionsEnum> actions = new ArrayList<>();
        actions.add(TopicActionsEnum.CREATE_COMMENT);
        actions.add(TopicActionsEnum.UPDATE);
        actions.add(TopicActionsEnum.DELETE);
        return actions;
    }

    // DUMMY DATA
    public static List<CommentActionsEnum> getCommentActions(){
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
        ext.setUsers(new ArrayList<>(Arrays.asList("admin")));
        ext.setStage(new ArrayList<>(Arrays.asList("Design", "Construction", "Review")));

        return ext;
    }
}
