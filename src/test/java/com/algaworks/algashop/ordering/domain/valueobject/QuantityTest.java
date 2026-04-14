package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QuantityTest {

    @Test
    void givenValidArgument_whenTryToInstantiate_thenShouldSucceed() {
        assertWith(Quantity.TEN, quantity -> assertThat(quantity.value()).isEqualTo(10L));
        assertWith(Quantity.ZERO, quantity -> assertThat(quantity.value()).isEqualTo(0L));
    }

    @Test
    void constantsShouldBeRight() {
        assertThat(Quantity.ZERO.value()).isEqualTo(0L);
        assertThat(Quantity.ONE.value()).isEqualTo(1L);
        assertThat(Quantity.TWO.value()).isEqualTo(2L);
        assertThat(Quantity.THREE.value()).isEqualTo(3L);
        assertThat(Quantity.FOUR.value()).isEqualTo(4L);
        assertThat(Quantity.FIVE.value()).isEqualTo(5L);
        assertThat(Quantity.SIX.value()).isEqualTo(6L);
        assertThat(Quantity.SEVEN.value()).isEqualTo(7L);
        assertThat(Quantity.EIGHT.value()).isEqualTo(8L);
        assertThat(Quantity.NINE.value()).isEqualTo(9L);
        assertThat(Quantity.TEN.value()).isEqualTo(10L);
    }

    @Test
    void compareToShouldWorkAsExpected() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> Quantity.ZERO.compareTo(null));

        assertWith(Quantity.TEN,
                q -> assertThat(q.compareTo(Quantity.ZERO)).isGreaterThan(0),
                q -> assertThat(q.compareTo(Quantity.TEN)).isEqualTo(0),
                q -> assertThat(q.compareTo(new Quantity(11L))).isLessThan(0)
        );
    }

    @Test
    void toStringShouldReturnAStringRepresentationOfValue() {
        assertWith(Quantity.TEN.toString(),
                q -> assertThat(q).isEqualTo("10"));
    }

    @Test
    void givenInvalidArguments_whenTryToInstantiate_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Quantity(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Quantity(-1L));
    }

    @Test
    void givenInvalidArgument_whenTryToAdd_thenShouldThrowException() {
        final Quantity originalQuantity = Quantity.TEN;

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> originalQuantity.add(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.add(-1L));
    }

    @Test
    void givenInvalidArgument_whenTryToSubtract_thenShouldThrowException() {
        final Quantity originalQuantity = Quantity.TEN;

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> originalQuantity.subtract(null));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.subtract(new Quantity(11L)));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.subtract(11L));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.subtract(-1L));
    }

    @Test
    void givenInvalidArgument_whenTryToMultiply_thenShouldThrowException() {
        final Quantity originalQuantity = Quantity.TEN;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.multiply(-1L));
    }

    @Test
    void givenInvalidArgument_whenTryToDivide_thenShouldThrowException() {
        final Quantity originalQuantity = Quantity.TEN;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.divide(0L));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> originalQuantity.divide(-1L));
    }

    @Test
    void givenValidArgument_whenTryToAdd_thenCorrectResultShouldBeReturned() {
        assertThat(Quantity.TEN.add(Quantity.ZERO)).isEqualTo(Quantity.TEN);
        assertThat(Quantity.TEN.add(Quantity.TEN)).isEqualTo(new Quantity(20L));
        assertThat(Quantity.TEN.add(0L)).isEqualTo(Quantity.TEN);
        assertThat(Quantity.TEN.add(10L)).isEqualTo(new Quantity(20L));
    }

    @Test
    void givenValidArgument_whenTryToSubtract_thenCorrectResultShouldBeReturned() {
        assertThat(Quantity.TEN.subtract(Quantity.ZERO)).isEqualTo(Quantity.TEN);
        assertThat(Quantity.TEN.subtract(Quantity.TEN)).isEqualTo(Quantity.ZERO);
        assertThat(Quantity.TEN.subtract(0L)).isEqualTo(Quantity.TEN);
        assertThat(Quantity.TEN.subtract(10L)).isEqualTo(Quantity.ZERO);
    }

    @Test
    void givenValidArgument_whenTryToMultiply_thenCorrectResultShouldBeReturned() {
        assertThat(Quantity.TEN.multiply(0L)).isEqualTo(Quantity.ZERO);
        assertThat(Quantity.TEN.multiply(10L)).isEqualTo(new Quantity(100L));
    }

    @Test
    void givenValidArgument_whenTryToDivide_thenCorrectResultShouldBeReturned() {
        assertThat(Quantity.TEN.divide(1L)).isEqualTo(Quantity.TEN);
        assertThat(Quantity.TEN.divide(10L)).isEqualTo(Quantity.ONE);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenAddWithLongOverflows() {
        Quantity quantity = new Quantity(Long.MAX_VALUE);

        assertThatThrownBy(() -> quantity.add(1L))
                .isInstanceOf(ArithmeticException.class);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenAddWithQuantityOverflows() {
        Quantity quantity = new Quantity(Long.MAX_VALUE);

        assertThatThrownBy(() -> quantity.add(Quantity.ONE))
                .isInstanceOf(ArithmeticException.class);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenMultiplyOverflows() {
        Quantity quantity = new Quantity(Long.MAX_VALUE);

        assertThatThrownBy(() -> quantity.multiply(2L))
                .isInstanceOf(ArithmeticException.class);
    }
}