package de.openfabtwin.services;

import de.openfabtwin.auth.UserRole;
import de.openfabtwin.entities.UserEntity;
import de.openfabtwin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void syncUserWithDatabase(Jwt jwt) {
        String userId = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");

        UserRole currentRole = UserRole.USER;
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof Collection<?> roles) {
            if (roles.stream().anyMatch(r -> r.toString().equalsIgnoreCase("ADMIN"))) {
                currentRole = UserRole.ADMIN;
            }
        }

        UserRole finalRole = currentRole;

        userRepository.findById(userId).ifPresentOrElse(
                user -> {
                    user.setName(name);
                    user.setEmail(email);
                    user.setRole(finalRole);
                    userRepository.save(user);
                },
                () -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setId(userId);
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setRole(finalRole);
                    userRepository.save(newUser);
                }
        );
    }
}
