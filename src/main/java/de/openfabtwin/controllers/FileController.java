package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.generated.api.FilesApi;
import de.openfabtwin.generated.dto.FileGET;
import de.openfabtwin.generated.dto.FilePUT;
import de.openfabtwin.generated.dto.ProjectFileInformation;
import de.openfabtwin.mappers.FileMapper;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.FileService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.utils.BcfProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController implements FilesApi {

    private final FileService fileService;
    private final FileMapper fileMapper;
    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final BcfProperties props;

    @Override
    public ResponseEntity<List<FileGET>> getFiles(String version, String projectId, String topicId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        List<FileGET> files = fileService.getAllFilesForTopic(projectId, topicId)
                .stream()
                .map(fileMapper::toFileGetDto)
                .toList();
        return ResponseEntity.ok(files);
    }

    @Override
    public ResponseEntity<List<ProjectFileInformation>> getProjectFilesInformation(String version, String projectId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        List<ProjectFileInformation> files =fileService.getAllProjectFileInformation(projectId)
                .stream()
                .map(fileMapper::toProjectFileDto)
                .toList();

        return ResponseEntity.ok(files);
    }

    @Override
    public ResponseEntity<List<FileGET>> updateTopicFile(String version, String projectId, String topicId, List<@Valid FilePUT> filePUT) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.UPDATE_FILES);
        List<FileGET> updatedFiles = fileService.updateTopicFiles(projectId, topicId, filePUT);
        return ResponseEntity.ok(updatedFiles);
    }
}
