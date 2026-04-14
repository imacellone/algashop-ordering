package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.validator.FieldValidations;

import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_PRODUCT_NAME;

public record ProductName(String value) implements Comparable<ProductName> {

    public ProductName {
        Objects.requireNonNull(value, VALIDATION_ERROR_INVALID_PRODUCT_NAME);
        FieldValidations.requireNonBlank(value);
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public int compareTo(final ProductName o) {
        Objects.requireNonNull(o, VALIDATION_ERROR_INVALID_PRODUCT_NAME);
        return this.value().compareTo(o.value());
    }
}
