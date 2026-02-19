package de.openfabtwin.controllers;

import de.openfabtwin.configs.OidcDiscoveryService;
import de.openfabtwin.generated.foundation.AuthGET;
import de.openfabtwin.generated.foundation.UserGET;
import de.openfabtwin.generated.foundation.VersionsGET;
import de.openfabtwin.generated.foundation.VersionsGETVersionsInner;
import de.openfabtwin.mappers.FoundationMapper;
import de.openfabtwin.services.SecurityContextService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FoundationController {

    @Value("${bcf.schema.version}")
    private String bcfVersion;

    @Value("${foundation.version}")
    private String foundationVersion;

    private final OidcDiscoveryService oidcDiscoveryService;
    private final SecurityContextService securityContextService;

    @GetMapping("/foundation/versions")
    public VersionsGET getVersions(HttpServletRequest request) {

        // Foundation API
        String foundationBaseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/foundation/{version}")
                .buildAndExpand(foundationVersion)
                .toUriString();
        VersionsGETVersionsInner foundationEntry = new VersionsGETVersionsInner();
        foundationEntry.setApiId("foundation");
        foundationEntry.setDetailedVersion("https://github.com/buildingSMART/foundation-API/tree/release_1_1");
        foundationEntry.setVersionId(foundationVersion);
        foundationEntry.setApiBaseUrl(foundationBaseUrl);

        //BCF API

        String bcfApiUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bcfEntry/{version}")
                .buildAndExpand(bcfVersion)
                .toUriString();
        VersionsGETVersionsInner bcfEntry = new VersionsGETVersionsInner();
        bcfEntry.setApiId("bcfEntry");
        bcfEntry.setVersionId(bcfVersion);
        bcfEntry.setDetailedVersion("https://github.com/buildingSMART/BCF-API/tree/release_3_0");
        bcfEntry.setApiBaseUrl(bcfApiUrl);

        VersionsGET response = new VersionsGET();
        response.setVersions(List.of(foundationEntry, bcfEntry));
        return response;
    }

    @GetMapping("/foundation/${foundation.version}/auth")
    public AuthGET getAuthMethods() {

        Map<String, Object> config = oidcDiscoveryService.getOpenIdConfiguration();

        AuthGET auth = new AuthGET();

        auth.setOauth2AuthUrl((String) config.get("authorization_endpoint"));
        auth.setOauth2TokenUrl((String) config.get("token_endpoint"));
        auth.setSupportedOauth2Flows(FoundationMapper.mapToOpenCdeGrants((List<String>) config.get("grant_types_supported")));

        auth.setHttpBasicSupported(false);

        return auth;
    }

    @GetMapping("/foundation/${foundation.version}/current-user")
    public UserGET getUser() {
        UserGET user = new UserGET();
        user.setId(securityContextService.getCurrentUserEmail());
        user.setName(securityContextService.getCurrentUserName());
        return user;
    }
}
