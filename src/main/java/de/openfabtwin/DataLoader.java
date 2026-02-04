package de.openfabtwin;


import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.*;
import de.openfabtwin.repositories.CommentRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicEventRepository;
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
import java.util.List;
import java.util.UUID;

@Component
@Profile("test")
public class DataLoader implements ApplicationRunner {

    private final ProjectRepository projectRepository;
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final TopicEventRepository topicEventRepository;


    @Autowired
    public DataLoader(ProjectRepository projectRepository, TopicRepository topicRepository, CommentRepository commentRepository, TopicEventRepository topicEventRepository) {
        this.projectRepository = projectRepository;
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
        this.topicEventRepository = topicEventRepository;
    }

    public void run(ApplicationArguments args) {
        String[] projectNames=  {"Project 1", "Project 2", "Project 3"};
        for (String projectName: projectNames) {
            ProjectEntity project = new ProjectEntity();
            project.setGuid(UUID.randomUUID().toString());
            project.setName(projectName);
            ExtensionEntity ext = createDefaultExtension(project);
            project.setExtensions(ext);

            projectRepository.save(project);

            // Create sample topics for each project
            createTopics(project);
        }
        System.out.println("🚀 Startup project initialization completed");
    }

    private void createTopics(ProjectEntity project) {
        String[] topicTitles = {"Topic A", "Topic B", "Topic C"};
        for (int i = 0; i < topicTitles.length; i++) {
            TopicEntity topic = new TopicEntity();
            topic.setGuid(UUID.randomUUID().toString());
            topic.setProject(project);
            topic.setTitle(topicTitles[i]);
            topic.setServerAssignedId(topicTitles[i].replace(" ", "-").toUpperCase());
            topic.setCreationAuthor("admin@localhost");
            Instant createTopicEventTime = Instant.now();
            topic.setCreationDate(createTopicEventTime);
            topic.setLabels(i > 1 ? new ArrayList<>(List.of("Interior")) : new ArrayList<>(List.of("Architecture", "Structure")));
            topic.setTopicType("Issue");
            topic.setTopicStatus(i > 1 ? "InProgress" : "Open");
            topic.setAssignedTo("user@localhost");
            topic.setPriority("High");
            topicRepository.save(topic);

            //topic Events
            TopicEventEntity topicEvent = new TopicEventEntity();
            topicEvent.setTopicGuid(topic.getGuid());
            topicEvent.setProjectGuid(project.getGuid());
            topicEvent.setAuthor("admin@localhost");
            topicEvent.setEventType(TopicEventType.topic_created);
            topicEvent.setEventValue(null);
            topicEvent.setEventDate(createTopicEventTime);
            topicEventRepository.save(topicEvent);

            // Create comments
            createComments(topic);

        }
    }


    private void createComments(TopicEntity topic) {
        String[] comments = {"test-comment-1", "test-comment-2", "test-comment-3"};

        for (String comment : comments) {
            CommentEntity cm = new CommentEntity();
            cm.setGuid(UUID.randomUUID().toString());
            cm.setTopic(topic);
            cm.setAuthor("user@localhost");
            cm.setDate(Instant.now());
            cm.setComment(comment);
            commentRepository.save(cm);
            topic.getComments().add(cm);
        }
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