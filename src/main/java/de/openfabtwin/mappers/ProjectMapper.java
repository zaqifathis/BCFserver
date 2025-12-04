package de.openfabtwin.mappers;

import de.openfabtwin.dto.ProjectGET;
import de.openfabtwin.dto.ProjectGETAuthorization;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.services.AuthorizationService;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectGET toDto(ProjectEntity entity) {
        var dto = new ProjectGET();
        dto.setProjectId(entity.getGuid());
        dto.setName(entity.getName());
        ProjectGETAuthorization auth = new ProjectGETAuthorization();
        auth.setProjectActions(AuthorizationService.getProjectActions());
        dto.setAuthorization(auth);
        return dto;
    }
}
