package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.validator.FieldValidations;

import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_QUANTITY;
import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_QUANTITY_SUBTRACTION;

public record Quantity(Long value) implements Comparable<Quantity> {

    public static final Quantity ZERO = new Quantity(0L);
    public static final Quantity ONE = new Quantity(1L);
    public static final Quantity TWO = new Quantity(2L);
    public static final Quantity THREE = new Quantity(3L);
    public static final Quantity FOUR = new Quantity(4L);
    public static final Quantity FIVE = new Quantity(5L);
    public static final Quantity SIX = new Quantity(6L);
    public static final Quantity SEVEN = new Quantity(7L);
    public static final Quantity EIGHT = new Quantity(8L);
    public static final Quantity NINE = new Quantity(9L);
    public static final Quantity TEN = new Quantity(10L);

    public Quantity {
        Objects.requireNonNull(value, VALIDATION_ERROR_INVALID_QUANTITY);
        FieldValidations.requireNonNegative(value, VALIDATION_ERROR_INVALID_QUANTITY);
    }

    public Quantity add(final long value) {
        return this.add(new Quantity(value));
    }

    public Quantity add(final Quantity other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_INVALID_QUANTITY);
        final long newValue = Math.addExact(this.value(), other.value());
        return new Quantity(newValue);
    }

    public Quantity subtract(final long value) {
        return this.subtract(new Quantity(value));
    }

    public Quantity subtract(final Quantity other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_INVALID_QUANTITY);
        if (other.value() > this.value()) {
            throw new IllegalArgumentException(VALIDATION_ERROR_INVALID_QUANTITY_SUBTRACTION);
        }
        return new Quantity(this.value() - other.value());
    }

    public Quantity multiply(final long value) {
        FieldValidations.requireNonNegative(value, VALIDATION_ERROR_INVALID_QUANTITY);
        final long newValue = Math.multiplyExact(this.value(), value);
        return new Quantity(newValue);
    }

    public Quantity divide(final long value) {
        FieldValidations.requirePositive(value, VALIDATION_ERROR_INVALID_QUANTITY);
        return new Quantity(this.value() / value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(final Quantity other) {
        Objects.requireNonNull(other, VALIDATION_ERROR_INVALID_QUANTITY);
        return this.value().compareTo(other.value());
    }

}
