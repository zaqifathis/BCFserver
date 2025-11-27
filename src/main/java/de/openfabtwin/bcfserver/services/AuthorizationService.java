package de.openfabtwin.bcfserver.services;

import de.openfabtwin.bcfserver.dto.ProjectGETAuthorization.ProjectActionsEnum;
import de.openfabtwin.bcfserver.entities.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationService {

    public static List<ProjectActionsEnum> getProjectActions(String role, ProjectEntity project) {

        List<ProjectActionsEnum> actions = new ArrayList<>();

        if ("admin".equals(role)) {
            actions.add(ProjectActionsEnum.UPDATE);
            actions.add(ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        } else {
            actions.add(ProjectActionsEnum.CREATE_TOPIC);
            actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        }
        return actions;
    }
}
