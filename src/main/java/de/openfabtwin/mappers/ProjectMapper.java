package de.openfabtwin.mappers;

import de.openfabtwin.ExtensionXmlParser;
import de.openfabtwin.entities.DocumentEntity;
import de.openfabtwin.generated.dto.DocumentGET;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.ProjectGET;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.generated.extensions.Extensions;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final ExtensionXmlParser extensionXmlParser;

    public ProjectMapper(ExtensionXmlParser extensionXmlParser) {
        this.extensionXmlParser = extensionXmlParser;
    }

    public ProjectGET toDto(ProjectEntity entity) {
        var dto = new ProjectGET();
        dto.setProjectId(entity.getGuid());
        dto.setName(entity.getName());
        return dto;
    }

    public ExtensionsGET toExtensionDto(ExtensionEntity ext) {
        Extensions xml = extensionXmlParser.parse(ext.getExtensionXml());
        var dto = new ExtensionsGET();

        if(xml.getTopicTypes() != null) {
            dto.setTopicType(xml.getTopicTypes().getTopicType());
        }
        if(xml.getTopicStatuses() != null) {
            dto.setTopicStatus(xml.getTopicStatuses().getTopicStatus());
        }
        if(xml.getPriorities() != null) {
            dto.setPriority(xml.getPriorities().getPriority());
        }
        if(xml.getTopicLabels() != null) {
            dto.setTopicLabel(xml.getTopicLabels().getTopicLabel());
        }
        if(xml.getUsers() != null) {
            dto.setUsers(xml.getUsers().getUser());
        }
        if(xml.getSnippetTypes() != null) {
            dto.setSnippetType(xml.getSnippetTypes().getSnippetType());
        }
        if(xml.getStages() != null) {
            dto.setStage(xml.getStages().getStage());
        }
        return dto;
    }

    public DocumentGET toDocumentDto(DocumentEntity documentEntity) {
        DocumentGET dto = new DocumentGET();
        dto.setGuid(documentEntity.getGuid());
        dto.setFilename(documentEntity.getFilename());
        return dto;
    }
}
