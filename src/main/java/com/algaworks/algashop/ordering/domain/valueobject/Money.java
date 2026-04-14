package com.algaworks.algashop.ordering.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_MONEY_VALUE;
import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_QUANTITY;
import static com.algaworks.algashop.ordering.utility.validator.FieldValidations.requireNonNegative;
import static com.algaworks.algashop.ordering.utility.validator.FieldValidations.requirePositive;
import static java.util.Objects.requireNonNull;

public record Money(BigDecimal value) implements Comparable<Money> {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(final BigDecimal value) {
        requireNonNull(value, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        final BigDecimal validValue = requireNonNegative(value, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        this.value = validValue.setScale(SCALE, ROUNDING_MODE);
    }

    public Money(final String value) {
        this(parse(value));
    }

    public Money multiply(final Quantity multiplicand) {
        requireNonNull(multiplicand, VALIDATION_ERROR_INVALID_QUANTITY);
        final Long operand = requirePositive(multiplicand.value(), VALIDATION_ERROR_INVALID_QUANTITY);
        return new Money(this.value().multiply(BigDecimal.valueOf(operand)));
    }

    public Money divide(final Money divisor) {
        requireNonNull(divisor, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        final BigDecimal divisorValue = divisor.value();
        requirePositive(divisorValue, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        return new Money(this.value().divide(divisorValue, SCALE, ROUNDING_MODE));
    }

    public Money add(final Money augend) {
        requireNonNull(augend, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        return new Money(this.value().add(augend.value()));
    }

    @Override
    public int compareTo(final Money other) {
        requireNonNull(other, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        return this.value().compareTo(other.value());
    }

    @Override
    public String toString() {
        return this.value().toString();
    }

    private static BigDecimal parse(final String value) {
        requireNonNull(value, VALIDATION_ERROR_INVALID_MONEY_VALUE);
        try {
            return new BigDecimal(value);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(VALIDATION_ERROR_INVALID_MONEY_VALUE, e);
        }
    }
}
