package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.utility.formatter.FieldFormatters;
import com.algaworks.algashop.ordering.utility.validator.FieldValidations;

import java.time.LocalDate;
import java.time.Period;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_INVALID_BIRTHDATE;

public record BirthDate(LocalDate value) {

    public BirthDate(final LocalDate value) {
        this.value = FieldValidations.requirePastDate(value, VALIDATION_ERROR_INVALID_BIRTHDATE);
    }

    public Integer age(final LocalDate on) {
        if (on == null || !this.value().isBefore(on)) {
            throw new IllegalArgumentException("Invalid date to be considered now");
        }
        return Period.between(this.value(), on).getYears();
    }

    @Override
    public String toString() {
        return FieldFormatters.format(this.value());
    }

}
