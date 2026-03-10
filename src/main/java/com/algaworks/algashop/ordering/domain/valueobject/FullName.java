package com.algaworks.algashop.ordering.domain.valueobject;

public record FullName(String firstName, String lastName) {

    public static final FullName ANONYMOUS = new FullName("Anonymous", "Anonymous");

    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Both first and last names must be provided");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
