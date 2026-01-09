package de.openfabtwin.auth;

public enum UserRole {

    // this is temporary, for initial data loading and testing purposes
    ADMIN("admin@localhost"),
    USER("user@localhost");

    private final String defaultAuthor;

    UserRole(String defaultAuthor) {
        this.defaultAuthor = defaultAuthor;
    }

    public String getDefaultAuthor() {
        return defaultAuthor;
    }
}
