package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.DocumentEntity;
import de.openfabtwin.generated.api.DocumentsApiOpt;
import de.openfabtwin.generated.dto.DocumentGET;
import de.openfabtwin.mappers.ProjectMapper;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.ProjectService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentController implements DocumentsApiOpt {

    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final BcfProperties props;


    @Override
    public ResponseEntity<DocumentGET> createDocument(String version, String projectId, String guid, Resource body) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Project.CREATE_DOCUMENT);
        DocumentEntity created = projectService.createDocument(projectId, guid, body);
        DocumentGET dto = projectMapper.toDocumentDto(created);
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<List<DocumentGET>> getDocument(String version, String projectId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        List<DocumentEntity> documents = projectService.getDocuments(projectId);
        List<DocumentGET> dtos = documents.stream()
                .map(projectMapper::toDocumentDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Resource> getDocumentById(String version, String projectId, String documentId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        DocumentEntity document = projectService.getDocument(projectId, documentId);
        Resource resource = new ByteArrayResource(document.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + document.getFilename() + "\""
                )
                .contentLength(document.getData().length)
                .body(resource);
    }
}
