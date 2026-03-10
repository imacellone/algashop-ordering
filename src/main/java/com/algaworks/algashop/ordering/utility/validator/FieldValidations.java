package com.algaworks.algashop.ordering.utility.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class FieldValidations {

    private FieldValidations() {
    }

    public static LocalDate requirePastDate(final LocalDate date) {
        return requirePastDate(date, null);
    }

    public static LocalDate requirePastDate(final LocalDate date, final String errorMessage) {
        if (date != null && date.isBefore(LocalDate.now())) {
            return date;
        }
        throw new IllegalArgumentException(errorMessage);
    }

    public static String requireNonBlank(final String value) {
        return requireNonBlank(value, null);
    }

    public static String requireNonBlank(final String value, final String errorMessage) {
        if (StringUtils.hasText(value)) {
            return value;
        }
        throw new IllegalArgumentException(errorMessage);
    }

    public static String requireValidEmail(final String email) {
        return requireValidEmail(email, null);
    }

    public static String requireValidEmail(final String email, final String errorMessage) {
        if (EmailValidator.getInstance().isValid(email)) {
            return email;
        }
        throw new IllegalArgumentException(errorMessage);
    }
}
