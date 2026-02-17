package de.openfabtwin.mappers;

import de.openfabtwin.entities.DocumentEntity;
import de.openfabtwin.generated.dto.DocumentGET;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGET;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectGET toDto(ProjectEntity entity) {
        var dto = new ProjectGET();
        dto.setProjectId(entity.getGuid());
        dto.setName(entity.getName());
        return dto;
    }

    public ExtensionsGET toExtensionDto(ExtensionEntity ext) {
        var dto = new ExtensionsGET();
        return dto;
    }

    public DocumentGET toDocumentDto(DocumentEntity documentEntity) {
        DocumentGET dto = new DocumentGET();
        dto.setGuid(documentEntity.getGuid());
        dto.setFilename(documentEntity.getFilename());
        return dto;
    }
}
