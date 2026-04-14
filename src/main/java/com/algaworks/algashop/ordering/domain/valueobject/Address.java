package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.validator.FieldValidations;
import lombok.Builder;

import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.*;

@Builder
public record Address(String street,
                      String number,
                      String complement,
                      String neighborhood,
                      String city,
                      String state,
                      ZipCode zipcode) {

    private static final String ANONYMOUS_WORD = "anonymous";

    public static final Address ANONYMOUS = Address.builder()
            .street(ANONYMOUS_WORD)
            .number(null)
            .complement(null)
            .neighborhood(ANONYMOUS_WORD)
            .city(ANONYMOUS_WORD)
            .state(ANONYMOUS_WORD)
            .zipcode(ZipCode.ANONYMOUS)
            .build();

    public Address {
        FieldValidations.requireNonBlank(street, VALIDATION_ERROR_INVALID_STREET);
        FieldValidations.requireNonBlank(neighborhood, VALIDATION_ERROR_INVALID_NEIGHBORHOOD);
        FieldValidations.requireNonBlank(city, VALIDATION_ERROR_INVALID_CITY);
        FieldValidations.requireNonBlank(state, VALIDATION_ERROR_INVALID_STATE);
        Objects.requireNonNull(zipcode, VALIDATION_ERROR_INVALID_ZIPCODE);
    }

}
