package com.algaworks.algashop.ordering.domain.valueobject;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LoyaltyPointsTest {

    @Test
    void shouldGenerateWithValue() {
        final LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
    void shouldGenerateWithZero() {
        final LoyaltyPoints loyaltyPoints = LoyaltyPoints.ZERO;
        assertThat(loyaltyPoints.value()).isEqualTo(0);
    }

    @Test
    void shouldAddValue() {
        final LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        final LoyaltyPoints addedPoints = loyaltyPoints.add(10);
        assertThat(loyaltyPoints.value()).isEqualTo(10);
        assertThat(addedPoints.value()).isEqualTo(20);
    }

    @Test
    void shouldNotAddValue() {
        final LoyaltyPoints loyaltyPoints = new LoyaltyPoints(10);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add(-5));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add((Integer) null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add((LoyaltyPoints) null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> loyaltyPoints.add(LoyaltyPoints.ZERO));
        assertThat(loyaltyPoints.value()).isEqualTo(10);
    }

    @Test
    void shouldNotInstantiateWithInvalidValues() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new LoyaltyPoints(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new LoyaltyPoints(-5));
    }

}