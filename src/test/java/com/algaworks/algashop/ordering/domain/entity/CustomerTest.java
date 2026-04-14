package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryToCreateCustomer_shouldThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CustomerTestDataBuilder.brandNewCustomerBuilder()
                        .email(null)
                        .build());
    }

    @Test
    void given_invalidEmail_whenTryToUpdateCustomerEmail_shouldThrowException() {
        final Customer customer = CustomerTestDataBuilder.brandNewCustomerBuilder().build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> customer.changeEmail(null));
    }

    @Test
    void given_unarchivedCustomer_whenArchived_shouldAnonymize() {
        final Customer customer = CustomerTestDataBuilder.brandNewCustomerBuilder().build();

        customer.archive();

        assertWith(customer,
                c -> assertThat(c.fullName()).isEqualTo(FullName.ANONYMOUS),
                c -> assertThat(c.email()).isNotEqualTo(new Email("john.doe@mail.com")),
                c -> assertThat(c.phone()).isEqualTo(new Phone("000-000-0000")),
                c -> assertThat(c.document()).isEqualTo(new Document("000-00-0000")),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.address()).isEqualTo(Address.ANONYMOUS));
    }

    @Test
    void given_archivedCustomer_whenArchived_shouldThrowException() {
        final Customer customer = CustomerTestDataBuilder.anonymizedCustomerBuilder().build();

        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(customer::archive);
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(customer::disablePromotionNotifications);
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(customer::enablePromotionNotifications);
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(() -> customer.addLoyaltyPoint(new LoyaltyPoints(1)));
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(() -> customer.changeName(new FullName("Jane", "Doe")));
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(() -> customer.changePhone(new Phone("111-111-1111")));
        assertThatExceptionOfType(CustomerArchivedException.class).isThrownBy(() -> customer.changeEmail(new Email("jane.doe@mail.com")));
    }

    @Test
    void given_newCustomer_whenAddLoyaltyPoints_shouldSum() {
        final Customer customer = CustomerTestDataBuilder.brandNewCustomerBuilder().build();
        customer.addLoyaltyPoint(new LoyaltyPoints(10));
        customer.addLoyaltyPoint(new LoyaltyPoints(20));
        assertThat(customer.loyaltyPoints()).isEqualTo(new LoyaltyPoints(30));
    }

    @Test
    void given_newCustomer_whenAddInvalidLoyaltyPoints_shouldThrowException() {
        final Customer customer = CustomerTestDataBuilder.brandNewCustomerBuilder().build();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> customer.addLoyaltyPoint(null));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> customer.addLoyaltyPoint(LoyaltyPoints.ZERO));

        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> CustomerTestDataBuilder.existingCustomerBuilder()
                .loyaltyPoints(null)
                .build());
    }
}