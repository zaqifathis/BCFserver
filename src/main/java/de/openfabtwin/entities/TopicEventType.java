package de.openfabtwin.entities;

public enum TopicEventType {
    topic_created,

    title_updated,

    description_updated,
    description_removed,

    status_updated,

    type_updated,

    priority_updated,
    priority_removed,

    due_date_updated,
    due_date_removed,

    assigned_to_updated,
    assigned_to_removed,

    label_added,
    label_removed,

    stage_added,
    stage_updated,
    stage_removed
}
