package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FullNameTest {

    @ParameterizedTest
    @MethodSource("invalidNames")
    void givenInvalidFullNames_whenTryToCreateFullName_thenExceptionShouldBeThrown(final String firstName,
                                                                                   final String lastName) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new FullName(firstName, lastName));
    }

    @ParameterizedTest
    @MethodSource("validNames")
    void givenValidFullNames_whenTryToCreateFullName_thenShouldSucceedAndTrim(final String firstName,
                                                                              final String lastName) {
        final String expectedFirstName = firstName.trim();
        final String expectedLastName = lastName.trim();
        final String expectedToString = expectedFirstName + " " + expectedLastName;

        assertWith(new FullName(firstName, lastName),
                fn -> assertThat(fn.firstName()).isEqualTo(expectedFirstName),
                fn -> assertThat(fn.lastName()).isEqualTo(expectedLastName),
                fn -> assertThat(fn.toString()).isEqualTo(expectedToString)
        );
    }

    private static Stream<Arguments> invalidNames() {
        final String[] bad = {null, "", " "};
        final String[] good = {"John"};

        final Stream<Arguments> badBad = Stream.of(bad)
                .flatMap(a -> Stream.of(bad).map(b -> arguments(a, b)));
        final Stream<Arguments> badGood = Stream.of(bad)
                .flatMap(a -> Stream.of(good).map(b -> arguments(a, b)));
        final Stream<Arguments> goodBad = Stream.of(good)
                .flatMap(a -> Stream.of(bad).map(b -> arguments(a, b)));

        return Stream.concat(badBad, Stream.concat(badGood, goodBad));
    }

    private static Stream<Arguments> validNames() {
        return Stream.of(
                arguments("John", "Doe"),
                arguments(" John ", "Doe"),
                arguments("John", " Doe "),
                arguments(" John ", " Doe ")
        );
    }
}