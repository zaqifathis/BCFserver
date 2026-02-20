package de.openfabtwin.services.security;

import java.util.List;

public interface IdentityProviderService {

    List<String> getGroupMembers(String groupId);

    // Add more generic methods later
}
