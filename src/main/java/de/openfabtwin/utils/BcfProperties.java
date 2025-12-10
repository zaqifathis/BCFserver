package de.openfabtwin.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bcf.schema")
@Data
public class BcfProperties {
    String version;

    public void validateVersion(String v) {
        if(!v.equals(this.version)) {
            throw new IllegalArgumentException("Unsupported BCF API version: " + v);
        }
    }
}
