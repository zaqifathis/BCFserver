package de.openfabtwin.mappers;

import de.openfabtwin.dto.generated.ExtensionsGET;
import de.openfabtwin.dto.generated.ProjectGET;
import de.openfabtwin.dto.generated.ProjectGETAuthorization;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.services.ProjectService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {

    public ProjectGET toDto(ProjectEntity entity) {
        var dto = new ProjectGET();
        dto.setProjectId(entity.getGuid());
        dto.setName(entity.getName());
        ProjectGETAuthorization auth = new ProjectGETAuthorization();
        auth.setProjectActions(ProjectService.getProjectActions());
        dto.setAuthorization(auth);
        return dto;
    }

    public ExtensionsGET toExtensionDto(ExtensionEntity ext) {
        var dto = new ExtensionsGET();
        dto.setTopicType(ext.getTopicType());
        dto.setTopicStatus(ext.getTopicStatus());
        dto.setTopicLabel(ext.getTopicLabel());
        dto.setSnippetType(ext.getSnippetType());
        dto.setPriority(ext.getPriority());
        dto.setUsers(ext.getUsers());
        dto.setStage(ext.getStage());
        dto.setProjectActions(List.of(ExtensionsGET.ProjectActionsEnum.UPDATE, ExtensionsGET.ProjectActionsEnum.CREATE_TOPIC, ExtensionsGET.ProjectActionsEnum.CREATE_DOCUMENT));
        dto.setCommentActions(ProjectService.getCommentActions()); //TODO: project actions should include in extensions
        dto.setTopicActions(ProjectService.getTopicActions());
        return dto;
    }
}
