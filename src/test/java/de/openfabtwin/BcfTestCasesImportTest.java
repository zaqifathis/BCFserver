package de.openfabtwin;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Seeds a running bcf-app with the official buildingSMART BCF 3.0 markup test cases by
 * calling {@code POST /import}. Auth settings are read from .env; when AUTH_ENABLED=true a
 * token is fetched with the password grant for a user that has the WRITE role.
 * Only runs when explicitly enabled:
 * <pre>./mvnw test -Dtest=BcfTestCasesImportTest -DbcfImport=true -DbcfUser=alice -DbcfPassword=secret</pre>
 * Optional: -DbcfServerUrl (default http://localhost:8181).
 */
@EnabledIfSystemProperty(named = "bcfImport", matches = "true",
        disabledReason = "Seeds the database: run with -DbcfImport=true")
class BcfTestCasesImportTest {

    private static final String TEST_CASES_URL =
            "https://raw.githubusercontent.com/buildingSMART/BCF-XML/release_3_0/Test%20Cases/v3.0/Markup/";

    private static final RestClient HTTP = RestClient.create();

    // Java ignores HTTPS_PROXY, so pass it explicitly for the GitHub downloads (e.g. behind a campus proxy)
    private static final RestClient GITHUB = RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory(HttpClient.newBuilder()
                    .proxy(proxyFromEnv())
                    .connectTimeout(Duration.ofSeconds(15))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build()))
            .build();

    private static String serverUrl;
    private static String accessToken;

    @BeforeAll
    static void authenticate() throws IOException {
        serverUrl = System.getProperty("bcfServerUrl", "http://localhost:8181");

        Properties env = new Properties();
        try (Reader reader = Files.newBufferedReader(Path.of(".env"))) {
            env.load(reader);
        }
        if (!Boolean.parseBoolean(env.getProperty("AUTH_ENABLED", "false"))) return;

        String tokenUrl = env.getProperty("AUTH_SERVER_URL") + "/realms/" + env.getProperty("AUTH_REALM")
                + "/protocol/openid-connect/token";

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", env.getProperty("AUTH_CLIENT_ID"));
        form.add("client_secret", env.getProperty("AUTH_CLIENT_SECRET"));
        form.add("username", requiredProperty("bcfUser"));
        form.add("password", requiredProperty("bcfPassword"));

        JsonNode token = HTTP.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(JsonNode.class);
        assertNotNull(token, "Empty token response from " + tokenUrl);
        accessToken = token.get("access_token").asText();
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "Document%20Reference%20External/document%20reference%20external.bcf",
            "Document%20reference%20internal/Document%20reference%20internal.bcf",
            "Due%20Date/DueDate.bcf",
            "Labels/Labels.bcf",
            "Milestone/Milestone.bcf",
            "Minimum%20information/Minimum%20information.bcf",
            "Related%20topics%20with%20both%20topics%20in%20the%20same%20file/Related%20topics%20with%20both%20topics%20in%20the%20same%20file.bcf",
            "User%20assignment/User%20assignment.bcf"
    })
    void importTestCase(String path) {
        byte[] bcf = GITHUB.get()
                .uri(URI.create(TEST_CASES_URL + path))
                .retrieve()
                .body(byte[].class);
        assertNotNull(bcf, "Download failed: " + TEST_CASES_URL + path);

        String filename = URLDecoder.decode(path.substring(path.lastIndexOf('/') + 1), StandardCharsets.UTF_8);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(bcf) {
            @Override
            public String getFilename() {
                return filename;
            }
        });

        ResponseEntity<String> response = HTTP.post()
                .uri(serverUrl + "/import")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .headers(headers -> {
                    if (accessToken != null) headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
                })
                .body(body)
                .retrieve()
                .toEntity(String.class);
        assertEquals(201, response.getStatusCode().value(), "Import failed: " + response.getBody());
    }

    private static ProxySelector proxyFromEnv() {
        String proxy = System.getenv("HTTPS_PROXY");
        if (proxy == null) proxy = System.getenv("https_proxy");
        if (proxy == null || proxy.isBlank()) return ProxySelector.getDefault();
        URI uri = URI.create(proxy);
        return ProxySelector.of(new InetSocketAddress(uri.getHost(), uri.getPort()));
    }

    private static String requiredProperty(String name) {
        String value = System.getProperty(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("AUTH_ENABLED=true in .env: pass -D" + name + "=...");
        }
        return value;
    }
}
