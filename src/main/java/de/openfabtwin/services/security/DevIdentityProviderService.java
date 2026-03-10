package de.openfabtwin.services.security;

import de.openfabtwin.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")  // only loads when test profile is active
@RequiredArgsConstructor
public class DevIdentityProviderService implements IdentityProviderService {

    private final ProjectRepository projectRepository;

    @Override
    public List<String> extractRoles(Jwt jwt) {
        return List.of("WRITE");
    }

    @Override
    public String extractUsername(Jwt jwt) {
        return "Dev User";
    }

    @Override
    public String extractEmail(Jwt jwt) {
        return "dev@localhost";
    }
}