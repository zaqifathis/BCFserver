package de.openfabtwin.services;

import de.openfabtwin.dto.ProjectGETAuthorization.ProjectActionsEnum;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.dto.ProjectGETAuthorization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationService {

    public static List<ProjectGETAuthorization.ProjectActionsEnum> getProjectActions() {
        List<ProjectActionsEnum> actions = new ArrayList<>();
        actions.add(ProjectActionsEnum.UPDATE);
        actions.add(ProjectActionsEnum.CREATE_TOPIC);
        actions.add(ProjectActionsEnum.CREATE_DOCUMENT);
        return actions;
    }
}
