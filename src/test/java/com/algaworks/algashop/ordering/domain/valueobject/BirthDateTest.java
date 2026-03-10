package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.algaworks.algashop.ordering.utility.formatter.FieldFormatters.format;
import static org.assertj.core.api.Assertions.*;

class BirthDateTest {

    @Test
    void givenInvalidBirthDates_whenTryToCreateBirthDate_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BirthDate(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BirthDate(LocalDate.now()));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BirthDate(LocalDate.now().plusDays(1)));
    }

    @Test
    void givenBirthDate_whenInvokeAgeMethod_thenShouldReturnCorrectAge() {
        assertWith(new BirthDate(LocalDate.of(1990, 1, 30)),
                d -> assertThat(d.age(LocalDate.of(1992, 1, 1))).isEqualTo(1));
        assertWith(new BirthDate(LocalDate.of(1990, 1, 30)),
                d -> assertThat(d.age(LocalDate.of(1992, 1, 30))).isEqualTo(2));
    }

    @Test
    void givenBirthDate_whenInvokeAgeMethodWithInvalidNow_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BirthDate(LocalDate.MAX).age(LocalDate.MIN));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BirthDate(LocalDate.now()).age(null));
    }

    @Test
    void givenBirthDate_whenInvokeToString_thenStringRepresentationOfDateShouldBeReturned() {
        final LocalDate birthDate = LocalDate.now().minusDays(1);
        assertWith(new BirthDate(birthDate), bd -> assertThat(bd.toString()).isEqualTo(format(birthDate)));
    }
}