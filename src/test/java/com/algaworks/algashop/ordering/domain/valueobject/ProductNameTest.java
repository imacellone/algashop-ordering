package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class ProductNameTest {

    @Test
    void givenInvalidArgument_whenTryToInstantiate_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new ProductName(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ProductName(""));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ProductName(" "));
    }

    @Test
    void givenValidArgument_whenTryToInstantiate_thenShouldSucceed() {
        final String productNameValue = "Roadrunner Catcher";
        final ProductName actual = new ProductName(productNameValue);
        assertThat(actual.value()).isEqualTo(productNameValue);
    }

    @Test
    void twoProductNamesShouldBeCorrectlyCompared() {
        final ProductName bar = new ProductName("Bar");
        final ProductName foo = new ProductName("Foo");
        assertThat(bar.compareTo(foo)).isLessThan(0);
        assertThat(foo.compareTo(bar)).isGreaterThan(0);

    }
}