package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ShippingInfoTest {

    @Test
    void givenNullArguments_whenTryToInstantiate_thenExceptionShouldBeThrown() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                ShippingInfo.builder()
                        .build());
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                ShippingInfo.builder()
                        .document(Document.ANONYMOUS)
                        .phone(Phone.ANONYMOUS)
                        .address(Address.ANONYMOUS)
                        .build());
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                ShippingInfo.builder()
                        .fullName(FullName.ANONYMOUS)
                        .phone(Phone.ANONYMOUS)
                        .address(Address.ANONYMOUS)
                        .build());
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                ShippingInfo.builder()
                        .fullName(FullName.ANONYMOUS)
                        .document(Document.ANONYMOUS)
                        .address(Address.ANONYMOUS)
                        .build());
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                ShippingInfo.builder()
                        .fullName(FullName.ANONYMOUS)
                        .document(Document.ANONYMOUS)
                        .phone(Phone.ANONYMOUS)
                        .build());
    }

    @Test
    void givenValidArguments_whenTryToInstantiate_thenShouldSucceed() {
        final ShippingInfo actual = ShippingInfo.builder()
                .fullName(FullName.ANONYMOUS)
                .phone(Phone.ANONYMOUS)
                .address(Address.ANONYMOUS)
                .document(Document.ANONYMOUS)
                .build();
        assertWith(actual,
                a -> assertThat(a).isNotNull(),
                a -> assertThat(a.fullName()).isNotNull(),
                a -> assertThat(a.document()).isNotNull(),
                a -> assertThat(a.phone()).isNotNull(),
                a -> assertThat(a.address()).isNotNull()
        );
    }
}