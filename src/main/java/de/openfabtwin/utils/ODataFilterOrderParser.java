package de.openfabtwin.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.regex.Pattern;

public class ODataFilterOrderParser {

    /* ============================
       ORDER BY
       ============================ */

    public static Sort parseOrderBy(
            String orderby,
            String defaultEntityField,
            Map<String, String> orderMapping
    ) {
        if (orderby == null || orderby.isBlank()) {
            return Sort.by(Sort.Direction.ASC, defaultEntityField);
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

    /* ============================
       FILTER (STRING + DATE only)
       ============================ */

    public static <T> Specification<T> getFilter(
            String filter,
            Map<String, String> filterMapping
    ) {
        return getFilter(filter, filterMapping, Map.of());
    }

    /* ============================
       FILTER (STRING + DATE + ENUM)
       ============================ */

    public static <T> Specification<T> getFilter(String filter, Map<String, String> filterMapping, Map<String, Class<? extends Enum<?>>> enumFieldMapping) {
        String normalized = filter.trim().replaceAll("\\s+", " ");
        String[] expressions = normalized.split("\\s+and\\s+");

        Specification<T> spec = Specification.unrestricted();

        for (String expr : expressions) {
            if (expr.startsWith("labels/any")) {
                spec = spec.and(parseLabelsAnyFilter(expr));
                continue;
            }

            if (expr.contains(" or ")) {
                String[] orExpressions = expr.split("\\s+or\\s+");
                Specification<T> orSpec = Specification.unrestricted();

                for (String orExpr : orExpressions) {
                    orSpec = orSpec.or(
                            parseSingleExpression(orExpr, filterMapping, enumFieldMapping)
                    );
                }

                spec = spec.and(orSpec);
                continue;
            }

            spec = spec.and(
                    parseSingleExpression(expr, filterMapping, enumFieldMapping)
            );
        }

        return spec;
    }

    /* ============================
       SINGLE EXPRESSION
       ============================ */

    private static <T> Specification<T> parseSingleExpression(
            String filter,
            Map<String, String> filterMapping,
            Map<String, Class<? extends Enum<?>>> enumFieldMapping
    ) {
        String[] parts = filter.trim().split("\\s+", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid $filter format: " + filter);
        }

        String apiField = parts[0];
        String operator = parts[1];
        String rawValue = parts[2].replaceAll("^'|'$", "");

        String entityField = filterMapping.get(apiField);
        if (entityField == null) {
            throw new IllegalArgumentException(
                    "Invalid $filter field according to BCF spec: " + apiField
            );
        }

        // ENUM field (topic events, comment events)
        Class<? extends Enum<?>> enumClass = enumFieldMapping.get(apiField);
        if (enumClass != null) {
            return enumFilter(entityField, operator, rawValue, enumClass);
        }

        // DATE field
        if (apiField.endsWith("date")) {
            return dateFilter(entityField, operator, rawValue);
        }

        // STRING field
        return stringFilter(entityField, operator, rawValue);
    }

    /* ============================
       STRING FILTER
       ============================ */

    private static <T> Specification<T> stringFilter(
            String field,
            String operator,
            String value
    ) {
        if (!operator.equalsIgnoreCase("eq")) {
            throw new IllegalArgumentException(
                    "Unsupported operator for string field: " + operator
            );
        }
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }

    /* ============================
       DATE FILTER
       ============================ */

    private static <T> Specification<T> dateFilter(
            String field,
            String operator,
            String value
    ) {
        Instant instantValue;
        try {
            instantValue = DateUtils.toInstant(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format in $filter: " + value
            );
        }

        return switch (operator) {
            case "eq" -> (root, query, cb) -> cb.equal(root.get(field), instantValue);
            case "gt" -> (root, query, cb) -> cb.greaterThan(root.get(field), instantValue);
            case "lt" -> (root, query, cb) -> cb.lessThan(root.get(field), instantValue);
            case "ge" -> (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(field), instantValue);
            case "le" -> (root, query, cb) -> cb.lessThanOrEqualTo(root.get(field), instantValue);
            default -> throw new IllegalArgumentException(
                    "Unsupported operator for date field: " + operator
            );
        };
    }

    /* ============================
       ENUM FILTER (GENERIC)
       ============================ */

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> Specification<T> enumFilter(
            String field,
            String operator,
            String value,
            Class<? extends Enum<?>> enumClass
    ) {
        if (!operator.equalsIgnoreCase("eq")) {
            throw new IllegalArgumentException(
                    "Unsupported operator for enum field: " + operator
            );
        }

        Enum enumValue;
        try {
            enumValue = Enum.valueOf((Class) enumClass, value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid enum value: " + value
            );
        }

        return (root, query, cb) -> cb.equal(root.get(field), enumValue);
    }

    /* ============================
       labels/any(...)
       ============================ */

    private static <T> Specification<T> parseLabelsAnyFilter(String filter) {
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
            throw new IllegalArgumentException(
                    "Invalid $filter format for labels/any: " + expr
            );
        }
    }
}
