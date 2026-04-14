package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PhoneTest {

    @Test
    void givenInvalidPhone_whenTryToCreatePhone_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Phone(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Phone(" "));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Phone(""));
    }

    @Test
    void givenValidPhone_whenTryToCreatePhone_thenShouldSucceed() {
        final String phone = "111-111-1111";
        assertWith(new Phone(phone),
                p -> assertThat(p.toString()).isEqualTo(phone),
                p -> assertThat(p.value()).isEqualTo(phone));
    }
}