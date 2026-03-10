package com.algaworks.algashop.ordering.utility.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;

public class FieldFormatters {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    private FieldFormatters() {
    }

    public static String format(final LocalDate date) {
        return Optional.ofNullable(date)
                .map(d -> d.format(DATE_TIME_FORMATTER))
                .orElse(null);
    }
}
