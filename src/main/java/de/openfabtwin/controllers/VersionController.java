package de.openfabtwin.controllers;

import de.openfabtwin.generated.foundation.VersionsGET;
import de.openfabtwin.generated.foundation.VersionsGETVersionsInner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VersionController {

    @Value("${bcf.schema.version}")
    private String bcfVersion;

    @GetMapping("/foundation/versions")
    public VersionsGET getVersions(HttpServletRequest request) {

        String baseUrl = buildBaseUrl(request);

        VersionsGETVersionsInner bcf30 = new VersionsGETVersionsInner();
        bcf30.setApiId("bcf");
        bcf30.setVersionId(bcfVersion);
        bcf30.setDetailedVersion("https://github.com/buildingSMART/BCF-API/tree/release_3_0");
        bcf30.setApiBaseUrl(baseUrl + "/bcf/" + bcfVersion + "/");

        VersionsGET response = new VersionsGET();
        response.setVersions(List.of(bcf30));

        return response;
    }

    private String buildBaseUrl(HttpServletRequest request) {
        String scheme = request.getHeader("X-Forwarded-Proto");
        if (scheme == null) {
            scheme = request.getScheme();
        }

        String host = request.getHeader("X-Forwarded-Host");
        if (host == null) {
            host = request.getServerName();
            int port = request.getServerPort();
            if (port != 80 && port != 443) {
                host += ":" + port;
            }
        }

        return scheme + "://" + host;
    }
}
