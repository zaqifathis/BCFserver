package de.openfabtwin.mappers;

import java.time.Instant;

public record TopicEventGroupKey(
        String topicGuid,
        String author,
        Instant eventDate
) {}

