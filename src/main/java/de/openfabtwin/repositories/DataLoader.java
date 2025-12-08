package de.openfabtwin.repositories;


import de.openfabtwin.entities.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Profile("test")
public class DataLoader implements ApplicationRunner {

    private final ProjectRepository projectRepository;

    @Autowired
    public DataLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void run(ApplicationArguments args) {
        String[] projectNames=  {"Project 1", "Project 2", "Project 3"};
        for (String projectName: projectNames) {
            ProjectEntity project = new ProjectEntity();
            project.setGuid(UUID.randomUUID().toString());
            project.setName(projectName);
            project.setAuthor("abc@localhost"); // TODO: remove field, possibly keep log?
            project.setCreatedAt(Instant.now().toString()); // TODO: remove field, possibly keep log?
            projectRepository.save(project);
        }
    }
}