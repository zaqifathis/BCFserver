package de.openfabtwin.controllers;

import de.openfabtwin.auth.Actions;
import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.generated.api.ViewpointsApi;
import de.openfabtwin.generated.dto.*;
import de.openfabtwin.mappers.ViewpointMapper;
import de.openfabtwin.services.AuthorizationAssembler;
import de.openfabtwin.services.AuthorizationService;
import de.openfabtwin.services.SecurityContextService;
import de.openfabtwin.services.ViewpointService;
import de.openfabtwin.services.ViewpointService.*;
import de.openfabtwin.services.ImageResult;
import de.openfabtwin.utils.BcfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewpointController implements ViewpointsApi {

    private final SecurityContextService securityContextService;
    private final AuthorizationService authorizationService;
    private final AuthorizationAssembler authorizationAssembler;
    private final ViewpointService viewpointService;
    private final ViewpointMapper viewpointMapper;
    private final BcfProperties props;

    @Override
    public ResponseEntity<ViewpointGET> createViewpoints(String version, String projectId, String topicId, ViewpointPOST viewpointPOST) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Topic.CREATE_VIEWPOINT);
        ViewpointEntity create = viewpointService.create(projectId, topicId, viewpointPOST);
        ViewpointGET dto = viewpointMapper.toDto(create);
        dto.setAuthorization(authorizationAssembler.viewpointAuthorization(role));
        return ResponseEntity.status(201).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteViewpointById(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        authorizationService.assertCan(role, Actions.Viewpoint.DELETE);
        viewpointService.deleteById(projectId, topicId, viewpointId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Resource> getBitmap(String version, String projectId, String topicId, String viewpointId, String bitmapId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        ImageResult bitmap = viewpointService.getBitmapResource(projectId, topicId, viewpointId, bitmapId);
        Resource resource = new ByteArrayResource(bitmap.imageData());
        MediaType mediaType = bitmap.imageType() == ImageType.PNG ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(bitmap.imageData().length)
                .body(resource);
    }

    @Override
    public ResponseEntity<ColoringGET> getColoring(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        List<Coloring> coloring = viewpointService.getColoring(projectId, topicId, viewpointId);
        ColoringGET dto = viewpointMapper.toColoringDto(coloring);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<SelectionGET> getSelection(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        List<Component> components = viewpointService.getSelectionComponents(projectId, topicId, viewpointId);
        SelectionGET dto = viewpointMapper.toSelectionDto(components);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Resource> getSnapshot(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        ImageResult snapshot = viewpointService.getSnapshotResource(projectId, topicId, viewpointId);

        Resource resource = new ByteArrayResource(snapshot.imageData());
        MediaType mediaType = snapshot.imageType() == ImageType.PNG ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(snapshot.imageData().length)
                .body(resource);
    }

    @Override
    public ResponseEntity<ViewpointGET> getViewpointById(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        ViewpointEntity vp = viewpointService.getById(projectId, topicId, viewpointId);
        ViewpointGET dto = viewpointMapper.toDto(vp);
        UserRole role = securityContextService.getCurrentUserRole();
        dto.setAuthorization(authorizationAssembler.viewpointAuthorization(role));
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<ViewpointGET>> getViewpoints(String version, String projectId, String topicId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        UserRole role = securityContextService.getCurrentUserRole();
        List<ViewpointGET> viewpoints = viewpointService.getAll(projectId, topicId)
                .stream()
                .map(vp -> {
                    ViewpointGET dto = viewpointMapper.toDto(vp);
                    dto.setAuthorization(authorizationAssembler.viewpointAuthorization(role));
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(viewpoints);
    }

    @Override
    public ResponseEntity<VisibilityGET> getVisibility(String version, String projectId, String topicId, String viewpointId) {
        props.validateVersion(version);
        boolean hasAccess = securityContextService.hasProjectAccess(projectId);
        if (!hasAccess) {
            throw new AccessDeniedException("User does not have access to project");
        }
        Visibility visibility = viewpointService.getVisibility(projectId, topicId, viewpointId);
        VisibilityGET dto = viewpointMapper.toVisibilityDto(visibility);
        return ResponseEntity.ok(dto);
    }
}
