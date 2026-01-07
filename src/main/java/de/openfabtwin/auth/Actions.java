package de.openfabtwin.auth;

public final class Actions {
    private Actions() {}

    public enum Project {
        UPDATE,
        CREATE_TOPIC,
        CREATE_DOCUMENT
    }

    public enum Topic {
        UPDATE,
        UPDATE_BIM_SNIPPET,
        UPDATE_RELATED_TOPICS,
        UPDATE_DOCUMENT_REFERENCES,
        UPDATE_FILES,
        CREATE_COMMENT,
        CREATE_VIEWPOINT,
        DELETE
    }

    public enum Comment {
        UPDATE,
        DELETE
    }
}
