package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.validator.FieldValidations;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_PHONE;

public record Phone(String value) {

    public static final Phone ANONYMOUS = new Phone("000-000-0000");

    public Phone(final String value) {
        this.value = FieldValidations.requireNonBlank(value, VALIDATION_ERROR_INVALID_PHONE);
    }

    @Override
    public String toString() {
        return this.value();
    }

}
