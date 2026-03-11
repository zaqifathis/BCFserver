package de.openfabtwin.services;

import de.openfabtwin.entities.*;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.services.bcfimport.BcfExtensionBuilder;
import de.openfabtwin.services.bcfimport.BcfTopicBuilder;
import de.openfabtwin.utils.BcfZipReader;
import de.openfabtwin.utils.BcfZipReader.BcfParseResult;
import de.openfabtwin.utils.BcfZipReader.TopicFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BcfXmlImportService {

    private final ProjectRepository projectRepository;
    private final BcfExtensionBuilder extensionBuilder;
    private final BcfTopicBuilder topicBuilder;

    public void validateFileExtension(String filename) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IOException("Filename is missing");
        }
        if (!filename.toLowerCase().endsWith(".bcf")) {
            throw new IOException("Invalid file type: '" + filename + "'. Only .bcf file is supported.");
        }
    }

    @Transactional
    public void importBcf(byte[] zipBytes) throws IOException {
        int projectCount = (int) projectRepository.count();
        BcfParseResult result = BcfZipReader.read(zipBytes, projectCount);

        String projectGuid = result.root().projectId();
        ProjectEntity existing = projectRepository.findByGuid(projectGuid).orElse(null);

        ProjectEntity project = existing != null
                ? mergeProject(existing, result)
                : buildProject(result);
        projectRepository.save(project);
    }

    // -----------------

    private ProjectEntity buildProject(BcfParseResult result) {
        var root = result.root();

        ProjectEntity project = new ProjectEntity();
        project.setGuid(root.projectId());
        project.setName(root.projectName());

        project.setExtensions(root.extensions() != null
                ? extensionBuilder.build(root.extensions(), project)
                : extensionBuilder.buildDefault(project));

        if (root.documentInfo() != null && root.documentInfo().getDocuments() != null) {
            for (var doc : root.documentInfo().getDocuments().getDocument()) {
                DocumentEntity docEntity = new DocumentEntity();
                docEntity.setProject(project);
                docEntity.setGuid(doc.getGuid());
                docEntity.setFilename(doc.getFilename());
                docEntity.setData(result.documents().get(doc.getGuid()));
                project.getDocuments().add(docEntity);
            }
        }

        for (TopicFolder folder : result.topics())
            project.getTopics().add(topicBuilder.build(folder, project));

        return project;
    }

    private ProjectEntity mergeProject(ProjectEntity existingProject, BcfParseResult result) {
        var root = result.root();

        existingProject.setName(root.projectName());

        ExtensionEntity ext = existingProject.getExtensions();
        if (root.extensions() != null) {
            extensionBuilder.update(ext, root.extensions());
        } else {
            extensionBuilder.clear(ext);
        }

        if (root.documentInfo() != null && root.documentInfo().getDocuments() != null) {
            for (var doc : root.documentInfo().getDocuments().getDocument()) {
                boolean alreadyExists = existingProject.getDocuments().stream()
                        .anyMatch(d -> d.getGuid().equals(doc.getGuid()));
                if (alreadyExists) continue;

                DocumentEntity docEntity = new DocumentEntity();
                docEntity.setProject(existingProject);
                docEntity.setGuid(doc.getGuid());
                docEntity.setFilename(doc.getFilename());
                docEntity.setData(result.documents().get(doc.getGuid()));
                existingProject.getDocuments().add(docEntity);
            }
        }

        for (TopicFolder folder : result.topics()) {
            existingProject.getTopics().stream()
                    .filter(t -> t.getGuid().equals(folder.guid()))
                    .findFirst()
                    .ifPresentOrElse(
                            existing -> topicBuilder.merge(folder, existing, existingProject),
                            () -> existingProject.getTopics().add(topicBuilder.build(folder, existingProject))
                    );
        }

        return existingProject;
    }
}