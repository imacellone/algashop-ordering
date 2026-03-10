package com.algaworks.algashop.ordering.domain.exception;

public class ErrorMessages {

    public static final String VALIDATION_ERROR_INVALID_PHONE = "The provided phone number is invalid";
    public static final String VALIDATION_ERROR_INVALID_BIRTHDATE = "The provided birth date is invalid";
    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "Full name must not be null";
    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "The email address is invalid";
    public static final String VALIDATION_ERROR_NULL_NEGATIVE_LOYALTY_POINTS = "Loyalty points cannot be null or negative";
    public static final String VALIDATION_ERROR_ADD_LOYALTY_POINTS = "Loyalty points to add must be greater than zero";
    public static final String VALIDATION_ERROR_INVALID_EMAIL = "The provided email is invalid";
    public static final String VALIDATION_ERROR_INVALID_DOCUMENT = "The provided document is invalid";
    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is already archived and cannot be modified";

    private ErrorMessages() {
    }
}
