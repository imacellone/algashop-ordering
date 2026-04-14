package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    @Test
    void zeroConstantShouldRepresentZero() {
        assertThat(Money.ZERO.value()).isZero();
    }

    @Test
    void givenInvalidArguments_whenTryToInstantiate_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Money((BigDecimal) null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money(BigDecimal.valueOf(-1)));
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Money((String) null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("-1"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money(" "));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money(""));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("a"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("-1 "));
    }

    @Test
    void givenInvalidQuantityArgument_whenTryToMultiply_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Money("10").multiply(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("10").multiply(Quantity.ZERO));
    }

    @Test
    void givenInvalidMoneyArgument_whenTryToDivide_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Money("10").divide(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("10").divide(Money.ZERO));
    }

    @Test
    void givenInvalidMoneyArgument_whenTryToAdd_thenShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Money("10").add(null));
    }

    @Test
    void givenInvalidMoney_whenTryToCompare_thenShouldThrowException() {
        assertWith(new Money("100"),
                money -> assertThat(money.compareTo(Money.ZERO)).isGreaterThan(0),
                money -> assertThat(money.compareTo(new Money("100"))).isZero(),
                money -> assertThat(money.compareTo(new Money("101"))).isLessThan(0)
        );
    }

    @Test
    void toStringShouldReturnValueAsString() {
        assertThat(Money.ZERO.toString()).isEqualTo("0.00");
        assertThat(new Money("123.33").toString()).isEqualTo("123.33");
        assertThat(new Money("123.00").toString()).isEqualTo("123.00");
    }

    @Test
    void givenValidArguments_whenTryToInstantiate_thenShouldSucceed() {
        assertThat(new Money("0").value().doubleValue()).isEqualTo(0.0);
        assertThat(new Money(BigDecimal.ZERO).value().doubleValue()).isEqualTo(0.0);
        assertThat(new Money("10").value().doubleValue()).isEqualTo(10.0);
        assertThat(new Money(BigDecimal.TEN).value().doubleValue()).isEqualTo(10.0);
    }

    @ParameterizedTest
    @MethodSource("scaleAndRoundingCases")
    void ensureScaleTwo_andRoundingHalfEven(final BigDecimal input) {
        final BigDecimal expected = input.setScale(SCALE, ROUNDING_MODE);
        assertThat(new Money(input).value()).isEqualTo(expected);
    }

    private static Stream<Arguments> scaleAndRoundingCases() {
        return Stream.of(
                Arguments.of(new BigDecimal("10.544")),
                Arguments.of(new BigDecimal("10.546")),
                Arguments.of(new BigDecimal("10.545")),
                Arguments.of(new BigDecimal("10.555")),
                Arguments.of(new BigDecimal("10.5")),
                Arguments.of(new BigDecimal("10"))
        );
    }

    @Test
    void givenValidArgument_whenTryToMultiplyByQuantity_thenShouldReturnCorrectMoney() {
        assertWith(new Money("10").multiply(new Quantity(10L)),
                actual -> assertThat(actual.value()).isEqualTo(new BigDecimal("100.00")));
    }

    @Test
    void givenValidArgument_whenTryToDivideByMoney_thenShouldReturnCorrectMoney() {
        assertWith(new Money("10").divide(new Money("10")),
                actual -> assertThat(actual.value()).isEqualTo(new BigDecimal("1.00")));
    }

    @Test
    void givenValidArgument_whenTryToMoney_thenShouldReturnCorrectMoney() {
        assertWith(new Money("10").add(new Money("10")),
                actual -> assertThat(actual.value()).isEqualTo(new BigDecimal("20.00")));
    }

}