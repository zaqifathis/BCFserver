package de.openfabtwin.controllers;

import de.openfabtwin.generated.foundation.AuthGET;
import de.openfabtwin.generated.foundation.VersionsGET;
import de.openfabtwin.generated.foundation.VersionsGETVersionsInner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class FoundationController {

    @Value("${bcf.schema.version}")
    private String bcfVersion;

    @GetMapping("/foundation/versions")
    public VersionsGET getVersions(HttpServletRequest request) {

        String bcfApiUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bcf/{version}")
                .buildAndExpand(bcfVersion)
                .toUriString();

        VersionsGETVersionsInner bcf30 = new VersionsGETVersionsInner();
        bcf30.setApiId("bcf");
        bcf30.setVersionId(bcfVersion);
        bcf30.setDetailedVersion("https://github.com/buildingSMART/BCF-API/tree/release_3_0");
        bcf30.setApiBaseUrl(bcfApiUrl);

        VersionsGET response = new VersionsGET();
        response.setVersions(List.of(bcf30));
        return response;
    }

    @GetMapping("/foundation/1.0/auth")
    public AuthGET getAuthMethods() {
        AuthGET auth = new AuthGET();

        auth.setOauth2AuthUrl("http://localhost:8180/realms/bcf-realm/protocol/openid-connect/auth");
        auth.setOauth2TokenUrl("http://localhost:8180/realms/bcf-realm/protocol/openid-connect/token");

        auth.setSupportedOauth2Flows(List.of(
                "authorization_code_grant",
                "resource_owner_password_credentials_grant"
        ));

        auth.setHttpBasicSupported(false);

        return auth;
    }
}
