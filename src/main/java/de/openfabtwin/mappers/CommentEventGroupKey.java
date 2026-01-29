package de.openfabtwin.mappers;

import java.time.Instant;

public record CommentEventGroupKey(
        String commentGuid,
        String topicGuid,
        String author,
        Instant eventDate
) {}
