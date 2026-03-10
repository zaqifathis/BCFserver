package de.openfabtwin.controllers;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.services.BcfXmlImportService;
import de.openfabtwin.services.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private final SecurityContextService securityContextService;
    private final BcfXmlImportService bcfXmlImportService;

    @ResponseBody
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importBcf(@RequestParam("file") MultipartFile file) {

        UserRole role = securityContextService.getCurrentUserRole();
        if (role != UserRole.WRITE) throw new AccessDeniedException("User has no access to import bcf file");
        if (file.isEmpty()) return ResponseEntity.badRequest().body("File is empty");

        try {
            bcfXmlImportService.validateFileExtension(file.getOriginalFilename());
            byte[] zipBytes = file.getBytes();
            bcfXmlImportService.importBcf(zipBytes);
            return ResponseEntity.status(201).build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
