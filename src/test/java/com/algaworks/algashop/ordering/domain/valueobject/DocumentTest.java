package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertWith;

class DocumentTest {

    @Test
    void givenInvalidDocumentString_whenTryToCreateDocument_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Document(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Document(""));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Document(" "));
    }

    @Test
    void givenValidDocumentString_whenTryToCreateDocument_thenShouldSucceed() {
        final String documentString = "00-000-000";
        assertWith(new Document(documentString), d -> assertThat(d.value()).isEqualTo(documentString));
    }

    @Test
    void givenValidDocument_whenCallToString_thenShouldReturnStringContainingValue() {
        assertWith(new Document("00-000-000"), d -> assertThat(d.value()).isEqualTo(d.toString()));
    }

    @Test
    void givenDocumentClass_whenAccessAnonymousConstant_thenShouldReturnDocumentRepresentingAnonymous() {
        assertWith(Document.ANONYMOUS, d -> assertThat(d.value()).isEqualTo("000-00-0000"));
    }

}