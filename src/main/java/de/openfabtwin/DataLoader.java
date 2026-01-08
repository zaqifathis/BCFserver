package de.openfabtwin;


import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Component
@Profile("test")
public class DataLoader implements ApplicationRunner {

    private final ProjectRepository projectRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public DataLoader(ProjectRepository projectRepository, TopicRepository topicRepository) {
        this.projectRepository = projectRepository;
        this.topicRepository = topicRepository;
    }

    public void run(ApplicationArguments args) {
        String[] projectNames=  {"Project 1", "Project 2", "Project 3"};
        for (String projectName: projectNames) {
            ProjectEntity project = new ProjectEntity();
            project.setGuid(UUID.randomUUID().toString());
            project.setName(projectName);
            project.setAuthor("admin@localhost"); // TODO: remove field, possibly keep log?
            project.setCreatedAt(Instant.now()); // TODO: remove field, possibly keep log?
            ExtensionEntity ext = createDefaultExtension(project);
            project.setExtensions(ext);

            projectRepository.save(project);

            // Create sample topics for each project
            String[] topicTitles = {"Topic A", "Topic B", "Topic C"};
            for (String topicTitle : topicTitles) {
                TopicEntity topic = new TopicEntity();
                topic.setGuid(UUID.randomUUID().toString());
                topic.setProject(project);
                topic.setTitle(topicTitle);
                topic.setServerAssignedId(topicTitle.replace(" ", "-").toUpperCase());
                topic.setCreationAuthor("admin@localhost");
                topic.setCreationDate(Instant.now());
                topic.setLabels(new ArrayList<>(Arrays.asList("Architecture", "Structure")));
                topic.setTopicType("Issue");
                topic.setTopicStatus("Open");
                topic.setAssignedTo("user@bcfserver");
                topic.setPriority("High");
                topicRepository.save(topic);
            }
        }
        System.out.println("🚀 Startup project initialization completed");
    }

    private ExtensionEntity createDefaultExtension(ProjectEntity project) {
        ExtensionEntity ext = new ExtensionEntity();
        ext.setProject(project);
        try {
            String xml = Files.readString(Path.of("src/test/testdata/default_extensions.xml"));
            ext.setExtensionXml(xml);
            return ext;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize default project extensions", e);
        }
    }
}