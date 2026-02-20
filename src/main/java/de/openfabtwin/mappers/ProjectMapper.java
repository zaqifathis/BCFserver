package de.openfabtwin.mappers;

import de.openfabtwin.entities.DocumentEntity;
import de.openfabtwin.generated.dto.DocumentGET;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGET;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.services.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final SecurityContextService securityContextService;

    public ProjectGET toDto(ProjectEntity entity) {
        var dto = new ProjectGET();
        dto.setProjectId(entity.getGuid());
        dto.setName(entity.getName());
        return dto;
    }

    public ExtensionsGET toExtensionDto(String projectId) {
        var dto = new ExtensionsGET();
        dto.setTopicType(List.of());
        dto.setTopicStatus(List.of());
        dto.setTopicLabel(List.of());
        dto.setSnippetType(List.of());
        dto.setPriority(List.of());
        dto.setStage(List.of());
        dto.setUsers(securityContextService.getUsersOnProject(projectId));
        return dto;
    }

    public DocumentGET toDocumentDto(DocumentEntity documentEntity) {
        DocumentGET dto = new DocumentGET();
        dto.setGuid(documentEntity.getGuid());
        dto.setFilename(documentEntity.getFilename());
        return dto;
    }
}
