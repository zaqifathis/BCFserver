package de.openfabtwin;

import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.generated.dto.ExtensionsGET.*;
import de.openfabtwin.repositories.ProjectRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Configuration
public class StartupProjectInitializer {

    private final ProjectRepository projectRepository;

    public StartupProjectInitializer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            for(int i= 0; i<3; i++) {
                createProject(i);
            }
            System.out.println("🚀 Startup project initialization completed");
        };
    }

    private void createProject(int index) {
        ProjectEntity project = new ProjectEntity();
        project.setGuid(UUID.randomUUID().toString());
        project.setName("Sample Project_" + (index + 1));
        project.setAuthor("admin@bcfserver");
        project.setCreatedAt(Instant.now());
        ExtensionEntity ext = createDefaultExtension(project);
        project.setExtensions(ext);;
        projectRepository.save(project);
    }

    private ExtensionEntity createDefaultExtension(ProjectEntity project) {
        ExtensionEntity ext = new ExtensionEntity();
        ext.setProject(project);
        ext.setTopicType(new ArrayList<>(Arrays.asList("Issue", "Info", "Request")));
        ext.setTopicStatus(new ArrayList<>(Arrays.asList("Open", "In Progress", "Closed")));
        ext.setTopicLabel(new ArrayList<>(Arrays.asList("Architecture", "Structure", "MEP")));
        ext.setSnippetType(new ArrayList<>(Arrays.asList("Screenshot", "ModelCutout")));
        ext.setPriority(new ArrayList<>(Arrays.asList("Low", "Medium", "High")));
        ext.setUsers(new ArrayList<>(Arrays.asList("admin@bcfserver", "user@bcfserver")));
        ext.setStage(new ArrayList<>(Arrays.asList("Design", "Construction", "Review")));
        ext.setProjectActions(new ArrayList<>(Arrays.asList("update", "createTopic", "createDocument")).stream()
                .map(ProjectActionsEnum::fromValue).toList());
        ext.setTopicActions(new ArrayList<>(Arrays.asList("update", "updateBimSnippet", "updateRelatedTopics", "updateDocumentReferences", "updateFiles", "createComment", "createViewpoint", "delete")).stream()
                .map(TopicActionsEnum::fromValue).toList());
        ext.setCommentActions(new ArrayList<>(Arrays.asList("update", "delete")).stream()
                .map(CommentActionsEnum::fromValue).toList());
        return ext;
    }
}
