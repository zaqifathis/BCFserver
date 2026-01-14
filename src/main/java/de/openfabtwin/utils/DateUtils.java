package de.openfabtwin.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateUtils {

    private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private DateUtils() {}

    private static TemporalAccessor parseBcfDateTime(String value) {
        if (value == null) return null;
        if (hasTimezone(value)) {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static Instant toInstant(String value) {
        TemporalAccessor ta = parseBcfDateTime(value);

        if (ta == null) return null;
        if (ta instanceof OffsetDateTime odt) {
            return odt.toInstant();
        }
        if (ta instanceof LocalDateTime ldt) {
            return ldt.toInstant(ZoneOffset.UTC);
        }
        throw new IllegalArgumentException("Unsupported type");
    }


    private static boolean hasTimezone(String date) {
        return date.endsWith("Z")
                || date.matches(".*[+-][0-9]{2}:[0-9]{2}$");
    }

    public static String toString(Instant instant) {
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    public static String toReadableDate(String date) {
        Instant instant = toInstant(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH).withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }

}
