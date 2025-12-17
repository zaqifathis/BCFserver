package de.openfabtwin.services;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.ExtensionEntity;
import de.openfabtwin.generated.dto.ExtensionsGET;
import de.openfabtwin.generated.dto.TopicPOST;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.generated.dto.TopicPUT;
import de.openfabtwin.mappers.TopicMapper;
import de.openfabtwin.repositories.ExtensionRepository;
import de.openfabtwin.repositories.ProjectRepository;
import de.openfabtwin.repositories.TopicRepository;
import de.openfabtwin.utils.DateUtils;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ExtensionRepository extensionRepository;
    private final ProjectRepository projectRepository;
    private final TopicMapper topicMapper;
    private final EntityManager entityManager;

    @Transactional
    public TopicEntity create(String projectId, TopicPOST topicPOST) {
        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));
        if (!extension.getProjectActions().contains(ExtensionsGET.ProjectActionsEnum.CREATE_TOPIC)) {
            throw new IllegalArgumentException("User does not have permission to create topics");
        }
        if(topicPOST.getTitle() == null || topicPOST.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        var topic = new TopicEntity();
        topic.setGuid(UUID.randomUUID().toString());
        topic.setTopicType(validateValue(topicPOST.getTopicType(), extension.getTopicType()));
        topic.setTopicStatus(validateValue(topicPOST.getTopicStatus(), extension.getTopicStatus()));
        topic.setReferenceLinks(topicPOST.getReferenceLinks());
        topic.setTitle(topicPOST.getTitle());
        topic.setPriority(validateValue(topicPOST.getPriority(), extension.getPriority()));
        topic.setIndex(topicPOST.getIndex());
        topic.setLabels(validateList(topicPOST.getLabels(), extension.getTopicLabel()));
        topic.setAssignedTo(validateValue(topicPOST.getAssignedTo(), extension.getUsers()));
        topic.setStage(validateValue(topicPOST.getStage(), extension.getStage()));
        topic.setDescription(topicPOST.getDescription());
        TemporalAccessor ta = DateUtils.parseBcfDateTime(topicPOST.getDueDate());
        topic.setDueDate(DateUtils.toInstant(ta));
        topic.setCreationAuthor("admin@bcfserver"); // TODO: set actual user
        topic.setCreationDate(Instant.now());
        topic.setProject(projectRepository.findByGuid(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId)));
        if(topicPOST.getBimSnippet() != null) {
            BimSnippetEntity snippetEntity = topicMapper.mapBimSnippetEntity(topicPOST.getBimSnippet(), topic);
            topic.setBimSnippet(snippetEntity);
        }
        topicRepository.save(topic);
        entityManager.flush();
        topic.setServerAssignedId("TOPIC_" + topic.getId());
        return topicRepository.save(topic);
    }

    public void delete(String topicId, String projectId) {
        TopicEntity topic = topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Could not delete topic: " + topicId + " not found in project: " + projectId));
        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));

        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.DELETE)) {
            throw new IllegalArgumentException("User does not have permission to delete topics");
        }
        topicRepository.delete(topic);
    }

    public TopicEntity getById(String topicId, String projectId) {
        return topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Topic: " + topicId + " not found in project: " + projectId));
    }

    public List<TopicEntity> getAll(String projectId, String filter, String orderby, String top, String skip) {

        int limit = (top != null) ? Integer.parseInt(top) : 100;
        int offset = (skip != null) ? Integer.parseInt(skip) : 0;
        int page = offset / limit;
        Sort sort = parseOrderBy(orderby);
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<TopicEntity> spec = (root, query, cb) -> cb.equal(root.get("project").get("guid"), projectId);
        if(filter != null && !filter.isBlank()) {
            spec = spec.and(fromFilter(filter));
        }

        Page<TopicEntity> topics = topicRepository.findAll(spec, pageable);
        return topics.getContent();
    }

    public TopicEntity update(String topicId, String projectId, TopicPUT topicPUT) {
        TopicEntity existingTopic = topicRepository.findByGuidAndProject_Guid(topicId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("Could not update topic: " + topicId + " not found in project: " + projectId));

        ExtensionEntity extension = extensionRepository.findByProject_Guid(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Extension not found for project: " + projectId));
        if (!extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE)) {
            throw new IllegalArgumentException("User does not have permission to update topics");
        }

        if (topicPUT.getTitle() == null) {
            throw new IllegalArgumentException("Title is required");
        }
        existingTopic.setTitle(topicPUT.getTitle());
        if(topicPUT.getTopicType() != null) {
            existingTopic.setTopicType(validateValue(topicPUT.getTopicType(), extension.getTopicType()));
        }
        if(topicPUT.getTopicStatus() != null) {
            existingTopic.setTopicStatus(validateValue(topicPUT.getTopicStatus(), extension.getTopicStatus()));
        }
        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE_DOCUMENT_REFERENCES) && topicPUT.getReferenceLinks() != null) {
            existingTopic.getReferenceLinks().clear();
            existingTopic.getReferenceLinks().addAll(topicPUT.getReferenceLinks());
        }
        if (topicPUT.getPriority() != null) {
            existingTopic.setPriority(validateValue(topicPUT.getPriority(), extension.getPriority()));
        }
        if(topicPUT.getIndex() != null) {
            existingTopic.setIndex(topicPUT.getIndex());
        }
        if(topicPUT.getLabels() != null) {
            List<String> labels = validateList(topicPUT.getLabels(), extension.getTopicLabel());
            existingTopic.getLabels().clear();
            existingTopic.getLabels().addAll(labels);
        }
        if(topicPUT.getAssignedTo() != null) {
            existingTopic.setAssignedTo(validateValue(topicPUT.getAssignedTo(), extension.getUsers()));
        }
        if(topicPUT.getStage() != null) {
            existingTopic.setStage(validateValue(topicPUT.getStage(), extension.getStage()));
        }
        if(topicPUT.getDescription() != null) {
            existingTopic.setDescription(topicPUT.getDescription());
        }
        if(extension.getTopicActions().contains(ExtensionsGET.TopicActionsEnum.UPDATE_BIM_SNIPPET) && topicPUT.getBimSnippet() != null) {
            BimSnippetEntity snippet = existingTopic.getBimSnippet();

            if (snippet == null) {
                snippet = new BimSnippetEntity();
                snippet.setTopic(existingTopic);
                snippet.setSnippetType(topicPUT.getBimSnippet().getSnippetType());
                snippet.setIsExternal(Boolean.parseBoolean(topicPUT.getBimSnippet().getIsExternal()));
                snippet.setReference(topicPUT.getBimSnippet().getReference());
                snippet.setReferenceSchema(topicPUT.getBimSnippet().getReferenceSchema());
                existingTopic.setBimSnippet(snippet);
            } else {
                topicMapper.updateBimSnippetEntity(topicPUT.getBimSnippet(), snippet);
            }
        }
        if(topicPUT.getDueDate() != null) {
            try {
                TemporalAccessor ta = DateUtils.parseBcfDateTime(topicPUT.getDueDate());
                existingTopic.setDueDate(DateUtils.toInstant(ta));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid due_date format, expected ISO-8601");
            }
        }
        return topicRepository.save(existingTopic);
    }

    //----------------- HELPER METHODS -----------------

    private <T> T validateValue(T target, List<T> validValues) {
        if (target == null) {
            return null;
        }
        if (!validValues.contains(target) && !validValues.isEmpty()) {
            throw new IllegalArgumentException("Invalid value: " + target);
        }
        return target;
    }

    private <T> List<T> validateList(List<T> targets, List<T> validList) {
        if(targets == null) {
            return List.of();
        }
        for (T target : targets) {
            if (!validList.contains(target) && !validList.isEmpty()) {
                throw new IllegalArgumentException("Invalid values: " + target);
            }
        }
        return targets;
    }

    private static final Map<String, String> TOPIC_FIELD_MAPPING = Map.ofEntries(
            Map.entry("creation_author", "creationAuthor"),
            Map.entry("modified_author", "modifiedAuthor"),
            Map.entry("assigned_to", "assignedTo"),
            Map.entry("stage", "stage"),
            Map.entry("topic_status", "topicStatus"),
            Map.entry("topic_type", "topicType"),
            Map.entry("priority", "priority"),
            Map.entry("creation_date", "creationDate"),
            Map.entry("modified_date", "modifiedDate")
    );

    private static Specification<TopicEntity> fromFilter(String filter) {
        if (filter == null || filter.isBlank()) {
            return Specification.unrestricted();
        }

        String normalized = filter.trim().replaceAll("\\s+", " ");
        String[] expressions = normalized.split("\\s+and\\s+");
        Specification<TopicEntity> spec = Specification.unrestricted();

        for (String expr : expressions) {
            Specification<TopicEntity> part;
            if (expr.startsWith("labels/any")) {
                part = (parselabelsAnyFilter(expr));
            } else {
                part = (parseSingleExpression(expr));
            }
            spec =spec.and(part);
        }
        return spec;
    }

    private static Specification<TopicEntity> parselabelsAnyFilter(String filter) {
        String[] expressions = filter.split("\\s+or\\s+");
        Specification<TopicEntity> spec = Specification.unrestricted();
        for (String expr : expressions) {
            spec = spec.or(parseSingleLabelAny(expr));
        }
        return spec;
    }

    private static Specification<TopicEntity> parseSingleLabelAny(String expr) {
        Pattern pattern = Pattern.compile(
                "labels/any\\(\\s*\\w+\\s*:\\s*\\w+\\s+eq\\s+'([^']+)'\\s*\\)"
        );
        var matcher = pattern.matcher(expr.trim());
        if (matcher.matches()) {
            String labelValue = matcher.group(1);
            return (root, query, cb) -> cb.isMember(labelValue, root.get("labels"));
        } else {
            throw new IllegalArgumentException("Invalid $filter format for labels/any: " + expr);
        }
    }

    private static Specification<TopicEntity> parseSingleExpression(String filter) {
        String[] parts = filter.trim().split("\\s+", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid $filter format: " + filter);
        }

        String apiField = parts[0];
        String operator = parts[1];
        String rawValue = parts[2].replaceAll("^'|'$", "");

        String entityField = TOPIC_FIELD_MAPPING.get(apiField);
        if (entityField == null) {
            throw new IllegalArgumentException("Invalid $filter field according to BCF spec: " + apiField);
        }

        if(apiField.endsWith("_date")) {
            return dateFilter(entityField, operator, rawValue);
        }
        return stringFilter(entityField, operator, rawValue);
    }

    private static Specification<TopicEntity> dateFilter(String field, String operator, String value) {
        Instant instantValue;
        try {
            TemporalAccessor ta = DateUtils.parseBcfDateTime(value);
            instantValue = DateUtils.toInstant(ta);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format in $filter: " + value);
        }

        return switch (operator) {
            case "eq" -> (root, query, cb) -> cb.equal(root.get(field), instantValue);
            case "gt" -> (root, query, cb) -> cb.greaterThan(root.get(field), instantValue);
            case "lt" -> (root, query, cb) -> cb.lessThan(root.get(field), instantValue);
            case "ge" -> (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(field), instantValue);
            case "le" -> (root, query, cb) -> cb.lessThanOrEqualTo(root.get(field), instantValue);
            default -> throw new IllegalArgumentException("Unsupported operator for date field: " + operator);
        };
    }

    private static Specification<TopicEntity> stringFilter(String field, String operator, String value) {
        if (!operator.equalsIgnoreCase("eq")) {
            throw new IllegalArgumentException("Unsupported operator for string field: " + operator);
        }
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }

    private Sort parseOrderBy(String orderby) {
        if (orderby == null || orderby.isBlank()) {
            return Sort.by(Sort.Direction.ASC, "creationDate");
        }
        String[] parts = orderby.trim().split("\\s+");
        String apiField = parts[0];
        String entityField = TOPIC_FIELD_MAPPING.get(apiField);

        if (entityField == null) {
            throw new IllegalArgumentException(
                    "Invalid $orderby field according to BCF spec: " + apiField
            );
        }
        Sort.Direction direction = Sort.Direction.ASC;
        if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        return Sort.by(direction, entityField);
    }

}
