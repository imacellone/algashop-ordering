package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.UUID;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_EMAIL;
import static com.algaworks.algashop.ordering.utility.validator.FieldValidations.requireValidEmail;

public record Email(String value) {

    private static final String ANONYMOUS_EMAIL_DOMAIN_PART = "@anonymous.com";

    public Email(final String value) {
        this.value = requireValidEmail(value, VALIDATION_ERROR_INVALID_EMAIL);
    }

    public static Email generateAnonymizedEmailBy(final UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("uuid must not be null");
        }
        return new Email(uuid + ANONYMOUS_EMAIL_DOMAIN_PART);
    }

    @Override
    public String toString() {
        return this.value();
    }
}
