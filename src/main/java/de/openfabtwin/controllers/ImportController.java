package de.openfabtwin.controllers;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.services.BcfXmlImportService;
import de.openfabtwin.services.SecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private static final Logger log = Logger.getLogger(ImportController.class.getName());

    private final SecurityContextService securityContextService;
    private final BcfXmlImportService bcfXmlImportService;

    public record ImportResult(String filename, int status, String error) {}

    @ResponseBody
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importBcf(@RequestParam("file") List<MultipartFile> files) {

        UserRole role = securityContextService.getCurrentUserRole();
        if (role != UserRole.WRITE) throw new AccessDeniedException("User has no access to import bcf file");
        if (files.isEmpty()) return ResponseEntity.badRequest().body("No file provided");

        // Single file keeps the original response: empty 201 or plain error message
        if (files.size() == 1) {
            ImportResult result = importFile(files.getFirst());
            return result.error() == null
                    ? ResponseEntity.status(result.status()).build()
                    : ResponseEntity.status(result.status()).body(result.error());
        }

        // Each file is imported in its own transaction, so a failing file does not roll back the others
        List<ImportResult> results = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                results.add(importFile(file));
            } catch (RuntimeException e) {
                log.log(Level.SEVERE, "Import failed for " + file.getOriginalFilename(), e);
                results.add(new ImportResult(file.getOriginalFilename(), 500, "Internal error while importing file"));
            }
        }

        boolean allSucceeded = results.stream().allMatch(r -> r.error() == null);
        return ResponseEntity.status(allSucceeded ? HttpStatus.CREATED : HttpStatus.MULTI_STATUS).body(results);
    }

    private ImportResult importFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty()) return new ImportResult(filename, 400, "File is empty");

        try {
            bcfXmlImportService.validateFileExtension(filename);
            bcfXmlImportService.importBcf(file.getBytes());
            return new ImportResult(filename, 201, null);
        } catch (UnsupportedOperationException e) {
            return new ImportResult(filename, 422, e.getMessage());
        } catch (IOException e) {
            return new ImportResult(filename, 400, e.getMessage());
        }
    }
}
