package com.algaworks.algashop.ordering.utility.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class FieldValidations {

    private FieldValidations() {
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
        Objects.requireNonNull(value, errorMessage);
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

    public static Long requireNonNegative(final long value) {
        return requireNonNegative(value, null);
    }

    public static Long requireNonNegative(final long value, final String message) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(message);
    }

    public static BigDecimal requireNonNegative(final BigDecimal value) {
        return requireNonNegative(value, null);
    }

    public static BigDecimal requireNonNegative(final BigDecimal value, final String message) {
        if (value.compareTo(BigDecimal.ZERO) >= 0) {
            return value;
        }
        throw new IllegalArgumentException(message);
    }

    public static Long requirePositive(final long value) {
        return requirePositive(value, null);
    }

    public static Long requirePositive(final long value, final String message) {
        if (value > 0) {
            return value;
        }
        throw new IllegalArgumentException(message);
    }

    public static BigDecimal requirePositive(final BigDecimal value) {
        return requirePositive(value, null);
    }

    public static BigDecimal requirePositive(final BigDecimal value, final String message) {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            return value;
        }
        throw new IllegalArgumentException(message);
    }
}
