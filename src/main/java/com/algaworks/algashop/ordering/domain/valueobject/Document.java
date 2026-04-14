package com.algaworks.algashop.ordering.domain.valueobject;

import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_DOCUMENT;
import static com.algaworks.algashop.ordering.utility.validator.FieldValidations.requireNonBlank;

public record Document(String value) {

    public static final Document ANONYMOUS = new Document("000-00-0000");

    public Document {
        Objects.requireNonNull(value, VALIDATION_ERROR_INVALID_DOCUMENT);
        requireNonBlank(value, VALIDATION_ERROR_INVALID_DOCUMENT);
    }

    @Override
    public String toString() {
        return this.value();
    }
}
