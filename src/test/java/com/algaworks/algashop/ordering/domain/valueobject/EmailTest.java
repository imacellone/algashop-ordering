package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class EmailTest {

    @Test
    void givenInvalidEmail_whenTryToCreate_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email(""));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email(" "));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email("john.doe@.com"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email("john.doe@mail.com."));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Email(" john.doe@mail.com"));
    }

    @Test
    void givenUuid_whenTryToGenerateAnonymizedEmail_thenEmailShouldBeCreated() {
        final UUID uuid = UUID.randomUUID();
        assertWith(Email.generateAnonymizedEmailBy(uuid),
                email -> assertThat(email.toString()).isEqualTo(uuid + "@anonymous.com"));
    }

    @Test
    void givenValidEmail_whenTryToCreateEmail_thenSucceeds() {
        final String emailString = "john.doe@mail.com";
        assertThat(new Email(emailString).toString()).isEqualTo(emailString);
    }
}