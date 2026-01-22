package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.DocumentReferenceEntity;
import de.openfabtwin.generated.api.DocumentReferencesApi;
import de.openfabtwin.generated.dto.DocumentReferenceGET;
import de.openfabtwin.generated.dto.DocumentReferencePOST;
import de.openfabtwin.generated.dto.DocumentReferencePUT;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.services.TopicService;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentReferenceController implements DocumentReferencesApi {

    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final BcfProperties props;

    @Override
    public ResponseEntity<DocumentReferenceGET> createDocumentReference(String version, String projectId, String topicId, DocumentReferencePOST documentReferencePOST) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.UPDATE_DOCUMENT_REFERENCES);
        DocumentReferenceEntity created = topicService.createDocumentReference(topicId, projectId, documentReferencePOST);
        DocumentReferenceGET dto = topicMapper.toDocumentReferenceDto(created);
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<List<DocumentReferenceGET>> getDocumentReferences(String version, String projectId, String topicId) {
        props.validateVersion(version);
        List<DocumentReferenceEntity> documentReferences = topicService.getDocumentReferences(topicId, projectId);
        List<DocumentReferenceGET> dtos = documentReferences.stream()
                .map(topicMapper::toDocumentReferenceDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<DocumentReferenceGET> updateDocumentReference(String version, String projectId, String topicId, String documentReferenceId, DocumentReferencePUT documentReferencePUT) {
        props.validateVersion(version);
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.UPDATE_DOCUMENT_REFERENCES);
        DocumentReferenceEntity updated = topicService.updateDocumentReference(topicId, projectId, documentReferenceId, documentReferencePUT);
        DocumentReferenceGET dto = topicMapper.toDocumentReferenceDto(updated);
        return ResponseEntity.ok(dto);
    }
}
