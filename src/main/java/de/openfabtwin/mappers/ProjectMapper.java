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
        auth.setProjectActions(ProjectService.getAuthorizedProjectActions("admin"));
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
        dto.setProjectActions(ProjectService.getExtensionProjectActions("admin"));
        dto.setCommentActions(ProjectService.getCommentActions("admin"));
        dto.setTopicActions(ProjectService.getTopicActions("admin"));
        return dto;
    }
}
