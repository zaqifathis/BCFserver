package de.openfabtwin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CaseUtils {

    private CaseUtils() {}

    public static String upper(String value) {
        return value == null ? null : value.toUpperCase(Locale.ROOT);
    }

    public static List<String> upper(List<String> values) {
        if (values == null) return new ArrayList<>();
        return new ArrayList<>(values.stream().map(CaseUtils::upper).distinct().toList());
    }
}
