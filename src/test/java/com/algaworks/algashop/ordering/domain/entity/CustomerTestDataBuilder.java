package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.utility.IdGenerator;

import java.time.LocalDate;

import static java.time.OffsetDateTime.now;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    public static Customer.BrandNewCustomerBuilder brandNewCustomerBuilder() {
        return Customer.brandNew()
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(1991, 7, 5)))
                .email(new Email("john.doe@mail.com"))
                .phone(new Phone("745-555-5555"))
                .document(new Document("000-00-0000"))
                .promotionNotificationsAllowed(false)
                .address(Address.ANONYMOUS);
    }

    public static Customer.ExistingCustomerBuilder anonymizedCustomerBuilder() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(FullName.ANONYMOUS)
                .birthDate(null)
                .email(Email.generateAnonymizedEmailBy(IdGenerator.generateTimeBasedUUID()))
                .phone(Phone.ANONYMOUS)
                .document(Document.ANONYMOUS)
                .promotionNotificationsAllowed(false)
                .archived(true)
                .registeredAt(now())
                .archivedAt(now())
                .loyaltyPoints(LoyaltyPoints.ZERO)
                .address(Address.ANONYMOUS);
    }

    public static Customer.ExistingCustomerBuilder existingCustomerBuilder() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("John", "Doe"))
                .birthDate(null)
                .email(new Email("john.doe@mail.com"))
                .phone(new Phone("000-000-0000"))
                .document(new Document("00-00-000"))
                .promotionNotificationsAllowed(false)
                .archived(false)
                .registeredAt(now())
                .archivedAt(null)
                .loyaltyPoints(null)
                .address(Address.ANONYMOUS);
    }
}
