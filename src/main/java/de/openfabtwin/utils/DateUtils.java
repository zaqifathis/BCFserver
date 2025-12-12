package de.openfabtwin.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private DateUtils() {}

    public static Instant toInstant(String date) {
        if (date == null) return null;
        if (hasTimezone(date)) {
            return Instant.parse(date);
        }
        LocalDateTime ldt = LocalDateTime.parse(date, LOCAL_DATE_TIME_FORMATTER);
        return ldt.toInstant(ZoneOffset.UTC);
    }

    private static boolean hasTimezone(String date) {
        return date.endsWith("Z")
                || date.matches(".*[+-][0-9]{2}:[0-9]{2}$");
    }

    public static String toString(Instant instant) {
        if (instant == null) return null;
        return instant.toString();
    }
}
