package de.openfabtwin.services.bcfimport;

import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.generated.extensions.Extensions;
import de.openfabtwin.services.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static de.openfabtwin.utils.CaseUtils.upper;

@Component
@RequiredArgsConstructor
public class BcfExtensionBuilder {

    private final SecurityContextService securityContextService;

    public ExtensionEntity build(Extensions extensions, ProjectEntity project) {
        ExtensionEntity entity = new ExtensionEntity();
        entity.setProject(project);

        if (extensions.getTopicTypes() != null && extensions.getTopicTypes().getTopicType() != null)
            entity.setTopicTypes(upper(extensions.getTopicTypes().getTopicType()));

        if (extensions.getTopicStatuses() != null && extensions.getTopicStatuses().getTopicStatus() != null)
            entity.setTopicStatuses(upper(extensions.getTopicStatuses().getTopicStatus()));

        if (extensions.getPriorities() != null && extensions.getPriorities().getPriority() != null)
            entity.setPriorities(upper(extensions.getPriorities().getPriority()));

        if (extensions.getTopicLabels() != null && extensions.getTopicLabels().getTopicLabel() != null)
            entity.setTopicLabels(upper(extensions.getTopicLabels().getTopicLabel()));

        if (extensions.getStages() != null && extensions.getStages().getStage() != null)
            entity.setStages(upper(extensions.getStages().getStage()));

        if (extensions.getSnippetTypes() != null && extensions.getSnippetTypes().getSnippetType() != null)
            entity.setSnippetTypes(new ArrayList<>(extensions.getSnippetTypes().getSnippetType()));

        if (extensions.getUsers() != null && extensions.getUsers().getUser() != null) {
            entity.setUsers(new ArrayList<>(extensions.getUsers().getUser()));
            String currentUser = securityContextService.getCurrentUserEmail();
            if (!extensions.getUsers().getUser().contains(currentUser)) entity.getUsers().add(currentUser);
        }
        return entity;
    }

    public ExtensionEntity buildDefault(ProjectEntity project) {
        ExtensionEntity ext = new ExtensionEntity();
        ext.setProject(project);
        return ext;
    }

    public void update(ExtensionEntity ext, Extensions extensions) {
        ext.setTopicTypes(extensions.getTopicTypes() != null && extensions.getTopicTypes().getTopicType() != null
                ? upper(extensions.getTopicTypes().getTopicType()) : new ArrayList<>());
        ext.setTopicStatuses(extensions.getTopicStatuses() != null && extensions.getTopicStatuses().getTopicStatus() != null
                ? upper(extensions.getTopicStatuses().getTopicStatus()) : new ArrayList<>());
        ext.setPriorities(extensions.getPriorities() != null && extensions.getPriorities().getPriority() != null
                ? upper(extensions.getPriorities().getPriority()) : new ArrayList<>());
        ext.setTopicLabels(extensions.getTopicLabels() != null && extensions.getTopicLabels().getTopicLabel() != null
                ? upper(extensions.getTopicLabels().getTopicLabel()) : new ArrayList<>());
        ext.setStages(extensions.getStages() != null && extensions.getStages().getStage() != null
                ? upper(extensions.getStages().getStage()) : new ArrayList<>());
        ext.setSnippetTypes(extensions.getSnippetTypes() != null && extensions.getSnippetTypes().getSnippetType() != null
                ? new ArrayList<>(extensions.getSnippetTypes().getSnippetType()) : new ArrayList<>());

        List<String> users = extensions.getUsers() != null && extensions.getUsers().getUser() != null
                ? new ArrayList<>(extensions.getUsers().getUser()) : new ArrayList<>();
        String currentUser = securityContextService.getCurrentUserEmail();
        if (!users.contains(currentUser)) users.add(currentUser);
        ext.setUsers(users);
    }

    public void clear(ExtensionEntity ext) {
        ext.setTopicTypes(new ArrayList<>());
        ext.setTopicStatuses(new ArrayList<>());
        ext.setPriorities(new ArrayList<>());
        ext.setTopicLabels(new ArrayList<>());
        ext.setStages(new ArrayList<>());
        ext.setSnippetTypes(new ArrayList<>());
        ext.setUsers(new ArrayList<>(List.of(securityContextService.getCurrentUserEmail())));
    }
}