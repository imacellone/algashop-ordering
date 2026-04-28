package com.algaworks.algashop.ordering.domain.exception;

public class ErrorMessages {

    public static final String VALIDATION_ERROR_INVALID_PHONE = "The provided phone number is invalid";
    public static final String VALIDATION_ERROR_INVALID_BIRTHDATE = "The provided birth date is invalid";
    public static final String VALIDATION_ERROR_FULL_NAME_IS_NULL = "Full name must not be null";
    public static final String VALIDATION_ERROR_INVALID_DOCUMENT = "The provided document is invalid";

    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "The email address is invalid";
    public static final String VALIDATION_ERROR_INVALID_EMAIL = "The provided email is invalid";

    public static final String VALIDATION_ERROR_NULL_NEGATIVE_LOYALTY_POINTS = "Loyalty points cannot be null or negative";
    public static final String VALIDATION_ERROR_ADD_LOYALTY_POINTS = "Loyalty points to add must be greater than zero";

    public static final String VALIDATION_ERROR_INVALID_ADDRESS = "The provided address is invalid";
    public static final String VALIDATION_ERROR_INVALID_STREET = "The provided street is invalid";
    public static final String VALIDATION_ERROR_INVALID_NEIGHBORHOOD = "The provided neighborhood is invalid";
    public static final String VALIDATION_ERROR_INVALID_CITY = "The provided city is invalid";
    public static final String VALIDATION_ERROR_INVALID_STATE = "The provided state is invalid";
    public static final String VALIDATION_ERROR_INVALID_ZIPCODE = "The provided zipcode is invalid";

    public static final String VALIDATION_ERROR_INVALID_QUANTITY = "The provided quantity is invalid";
    public static final String VALIDATION_ERROR_INVALID_QUANTITY_SUBTRACTION = "Subtraction of Quantities would result in a negative Quantity";

    public static final String VALIDATION_ERROR_INVALID_MONEY_VALUE = "the provided money value is invalid";
    public static final String VALIDATION_ERROR_INVALID_PRODUCT_NAME = "The provided product name is invalid";

    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is already archived and cannot be modified";

    public static final String ERROR_ORDER_STATUS_CANNOT_BE_CHANGED = "Cannot change order %s status from %s to %s";

    private ErrorMessages() {
    }
}
