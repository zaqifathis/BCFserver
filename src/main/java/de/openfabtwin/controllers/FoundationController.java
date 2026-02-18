package de.openfabtwin.controllers;

import de.openfabtwin.configs.OidcDiscoveryService;
import de.openfabtwin.generated.foundation.AuthGET;
import de.openfabtwin.generated.foundation.VersionsGET;
import de.openfabtwin.generated.foundation.VersionsGETVersionsInner;
import de.openfabtwin.mappers.FoundationMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@Data
public class FoundationController {

    @Value("${bcf.schema.version}")
    private String bcfVersion;

    private final OidcDiscoveryService oidcDiscoveryService;

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

        Map<String, Object> config = oidcDiscoveryService.getOpenIdConfiguration();

        AuthGET auth = new AuthGET();

        auth.setOauth2AuthUrl((String) config.get("authorization_endpoint"));
        auth.setOauth2TokenUrl((String) config.get("token_endpoint"));
        auth.setSupportedOauth2Flows(FoundationMapper.mapToOpenCdeGrants((List<String>) config.get("grant_types_supported")));

        auth.setHttpBasicSupported(false);

        return auth;
    }
}
