package com.algaworks.algashop.ordering.domain.valueobject;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_ADD_LOYALTY_POINTS;
import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_NULL_NEGATIVE_LOYALTY_POINTS;

public record LoyaltyPoints(Integer value) implements Comparable<LoyaltyPoints> {

    public static final LoyaltyPoints ZERO = new LoyaltyPoints(0);

    public LoyaltyPoints {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_NULL_NEGATIVE_LOYALTY_POINTS);
        }
    }

    public LoyaltyPoints add(final LoyaltyPoints points) {
        if (points == null || points.value() <= 0) {
            throw new IllegalArgumentException(VALIDATION_ERROR_ADD_LOYALTY_POINTS);
        }
        return new LoyaltyPoints(this.value() + points.value());
    }

    public LoyaltyPoints add(final Integer points) {
        return add(new LoyaltyPoints(points));
    }

    @Override
    public String toString() {
        return this.value().toString();
    }

    @Override
    public int compareTo(final LoyaltyPoints other) {
        return this.value.compareTo(other.value());
    }
}
