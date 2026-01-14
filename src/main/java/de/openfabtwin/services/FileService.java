package de.openfabtwin.services;


import de.openfabtwin.entities.FileEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.TopicFileReferenceEntity;
import de.openfabtwin.generated.dto.FileGET;
import de.openfabtwin.generated.dto.FilePUT;
import de.openfabtwin.mappers.FileMapper;
import de.openfabtwin.repositories.FileRepository;
import de.openfabtwin.repositories.TopicFileReferenceRepository;
import de.openfabtwin.repositories.TopicRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final TopicFileReferenceRepository topicFileReferenceRepository;
    private final EntityResolver entityResolver;
    private final TopicRepository topicRepository;
    private final FileMapper fileMapper;

    public List<FileEntity> getAllProjectFileInformation(String projectId) {
        return fileRepository.findByProject_Guid(projectId);
    }

    public List<TopicFileReferenceEntity> getAllFilesForTopic(String projectId, String topicId) {
        entityResolver.resolveTopic(projectId, topicId);
        return topicFileReferenceRepository.findByTopic_Project_GuidAndTopic_Guid(projectId, topicId);
    }

    public List<FileGET> updateTopicFiles(String projectId, String topicId, List<@Valid FilePUT> filePUT) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        topic.getFileReferences().clear();

        for (FilePUT dto: filePUT) {
            FileEntity file = resolveOrCreateFileEntity(projectId, dto);
            TopicFileReferenceEntity reference = new TopicFileReferenceEntity();
            reference.setTopic(topic);
            reference.setFile(file);
            reference.setIfcProjectGuid(dto.getIfcProject() != null ? dto.getIfcProject() : "");
            reference.setIfcSpatialStructureElementGuid(dto.getIfcSpatialStructureElement() != null ? dto.getIfcSpatialStructureElement() : "");
            topic.getFileReferences().add(reference);
        }

        topicRepository.save(topic);
        return topic.getFileReferences().stream()
                .map(fileMapper::toFileGetDto)
                .toList();
    }

    private FileEntity resolveOrCreateFileEntity(String projectId, FilePUT dto) {
        return fileRepository.findByProject_GuidAndReference(projectId, dto.getReference())
                .orElseGet(() -> {
                    FileEntity newFile = new FileEntity();
                    newFile.setProject(entityResolver.resolveProject(projectId));
                    newFile.setFilename(dto.getFilename());
                    newFile.setReference(dto.getReference());
                    newFile.setDate(dto.getDate());
                    newFile.setExternal(true);
                    return fileRepository.save(newFile);
                });
    }
}
