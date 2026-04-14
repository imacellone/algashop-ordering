package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.validator.FieldValidations;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_ZIPCODE;

public record ZipCode(String value) {

    public static final ZipCode ANONYMOUS = new ZipCode("00000");

    public ZipCode {
        FieldValidations.requireNonBlank(value, VALIDATION_ERROR_INVALID_ZIPCODE);
        if (value.length() != 5) {
            throw new IllegalArgumentException(VALIDATION_ERROR_INVALID_ZIPCODE);
        }
    }

    @Override
    public String toString() {
        return this.value;
    }
}
