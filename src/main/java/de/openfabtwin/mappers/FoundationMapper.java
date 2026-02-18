package de.openfabtwin.mappers;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FoundationMapper {
    public static List<String> mapToOpenCdeGrants(List<String> providerGrants) {

        if (providerGrants == null) {
            return List.of();
        }

        return providerGrants.stream()
                .map(grant -> switch (grant) {
                    case "authorization_code" ->
                            "authorization_code_grant";
                    case "implicit" ->
                            "implicit_grant";
                    case "password" ->
                            "resource_owner_password_credentials_grant";
                    default -> null; // filter everything else
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
