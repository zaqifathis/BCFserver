package de.openfabtwin.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.regex.Pattern;

public class ODataFilterOrderParser {

    public static Sort parseOrderBy(String orderby, String defaultOrder, Map<String, String> orderMapping) {
        if (orderby == null || orderby.isBlank()) {
            return Sort.by(Sort.Direction.ASC, defaultOrder);
        }
        String[] parts = orderby.trim().split("\\s+");
        String apiField = parts[0];
        String entityField = orderMapping.get(apiField);

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

    public static <T> Specification<T> getFilter(String filter, Map<String, String> filterMapping) {

        String normalized = filter.trim().replaceAll("\\s+", " ");
        String[] expressions = normalized.split("\\s+and\\s+");
        Specification<T> spec = Specification.unrestricted();

        for (String expr : expressions) {
            Specification<T> part;
            if (expr.startsWith("labels/any")) {
                part = parselabelsAnyFilter(expr);
            } else {
                part = parseSingleExpression(expr, filterMapping);
            }
            spec =spec.and(part);
        }
        return spec;
    }

    private static <T> Specification<T> parselabelsAnyFilter(String filter) {
        String[] expressions = filter.split("\\s+or\\s+");
        Specification<T> spec = Specification.unrestricted();
        for (String expr : expressions) {
            spec = spec.or(parseSingleLabelAny(expr));
        }
        return spec;
    }

    private static <T> Specification<T> parseSingleLabelAny(String expr) {
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

    private static <T> Specification<T> parseSingleExpression(String filter, Map<String, String> filterMapping) {
        String[] parts = filter.trim().split("\\s+", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid $filter format: " + filter);
        }

        String apiField = parts[0];
        String operator = parts[1];
        String rawValue = parts[2].replaceAll("^'|'$", "");

        String entityField = filterMapping.get(apiField);
        if (entityField == null) {
            throw new IllegalArgumentException("Invalid $filter field according to BCF spec: " + apiField);
        }

        if(apiField.endsWith("date")) {
            return dateFilter(entityField, operator, rawValue);
        }
        return stringFilter(entityField, operator, rawValue);

    }

    private static <T> Specification<T> stringFilter(String field, String operator, String value) {
        if (!operator.equalsIgnoreCase("eq")) {
            throw new IllegalArgumentException("Unsupported operator for string field: " + operator);
        }
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }

    private static <T> Specification<T> dateFilter(String field, String operator, String value) {
        Instant instantValue;
        try {
            instantValue = DateUtils.toInstant(value);
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



}
