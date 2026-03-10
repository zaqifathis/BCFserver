package de.openfabtwin.services;

import de.openfabtwin.entities.DocumentEntity;
import de.openfabtwin.exceptions.ConflictException;
import de.openfabtwin.generated.dto.ProjectPUT;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.entities.ProjectEntity;
import de.openfabtwin.repositories.DocumentRepository;
import de.openfabtwin.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;
    private final EntityResolver entityResolver;

    public List<ProjectEntity> getAllProjects(List<String> guids) {
        if (guids.isEmpty()) return List.of();
        return projectRepository.findAllByGuidIn(guids);
    }

    public ProjectEntity getProject(String guid) {
        return entityResolver.resolveProject(guid);
    }

    public ProjectEntity update(String guid, ProjectPUT dto) {
        ProjectEntity project = entityResolver.resolveProject(guid);
        project.setName(dto.getName());
        return projectRepository.save(project);
    }

    public ExtensionEntity getProjectExtension(String projectId) {
        return entityResolver.resolveProjectExtension(projectId);
    }

    public List<DocumentEntity> getDocuments(String projectId) {
        ProjectEntity project = entityResolver.resolveProject(projectId);
        return project.getDocuments();
    }

    public DocumentEntity createDocument(String projectId, String requestedGuid, Resource body) {
        ProjectEntity project = entityResolver.resolveProject(projectId);
        DocumentEntity document = new DocumentEntity();

        String guid = requestedGuid != null ? requestedGuid : UUID.randomUUID().toString();
        if (documentRepository.existsByGuidAndProject_Guid(guid, projectId)) {
            throw new ConflictException("Document with GUID already exists in project");
        }
        document.setGuid(guid);

        try (InputStream in = body.getInputStream()) {
            byte[] data = in.readAllBytes();
            if (data.length == 0) throw new IllegalArgumentException("Document data is empty");

            String filename = body.getFilename();
            if (filename == null) filename = "document.bin";

            document.setFilename(filename);
            document.setData(data);
            document.setProject(project);
            project.getDocuments().add(document);
            projectRepository.save(project);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read document data", e);

        }
        return document;
    }

    public DocumentEntity getDocument(String projectId, String documentId) {
        return entityResolver.resolveDocument(projectId, documentId);
    }
}
