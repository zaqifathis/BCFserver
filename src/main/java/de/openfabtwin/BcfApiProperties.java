package de.openfabtwin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bcf.api")
@Data
public class BcfApiProperties {
    String version;
}
