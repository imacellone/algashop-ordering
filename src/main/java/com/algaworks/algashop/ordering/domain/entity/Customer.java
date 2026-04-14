package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.*;

public class Customer {

    private CustomerId id;
    private FullName fullName;
    private BirthDate birthDate;
    private Email email;
    private Phone phone;
    private Document document;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private LoyaltyPoints loyaltyPoints;
    private Address address;

    @Builder(builderClassName = "BrandNewCustomerBuilder", builderMethodName = "brandNew")
    private Customer(final FullName fullName,
                     final BirthDate birthDate,
                     final Email email,
                     final Phone phone,
                     final Document document,
                     final Boolean promotionNotificationsAllowed,
                     final Address address) {
        this(new CustomerId(),
                fullName,
                birthDate,
                email,
                phone,
                document,
                promotionNotificationsAllowed,
                false,
                OffsetDateTime.now(),
                null,
                LoyaltyPoints.ZERO,
                address);
    }

    @Builder(builderClassName = "ExistingCustomerBuilder", builderMethodName = "existing")
    private Customer(final CustomerId id,
                     final FullName fullName,
                     final BirthDate birthDate,
                     final Email email,
                     final Phone phone,
                     final Document document,
                     final Boolean promotionNotificationsAllowed,
                     final Boolean archived,
                     final OffsetDateTime registeredAt,
                     final OffsetDateTime archivedAt,
                     final LoyaltyPoints loyaltyPoints,
                     final Address address) {
        setId(id);
        setFullName(fullName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
        setDocument(document);
        setPromotionNotificationsAllowed(promotionNotificationsAllowed);
        setArchived(archived);
        setRegisteredAt(registeredAt);
        setArchivedAt(archivedAt);
        setLoyaltyPoints(loyaltyPoints);
        setAddress(address);
    }

    public void addLoyaltyPoint(final LoyaltyPoints addPointsAmount) {
        requireChangeable();
        final LoyaltyPoints addedPoints = this.loyaltyPoints().add(addPointsAmount);
        this.setLoyaltyPoints(addedPoints);
    }

    public void archive() {
        requireChangeable();
        this.setArchivedAt(OffsetDateTime.now());
        this.setArchived(true);
        this.setFullName(FullName.ANONYMOUS);
        this.setPhone(Phone.ANONYMOUS);
        this.setDocument(Document.ANONYMOUS);
        this.setEmail(Email.generateAnonymizedEmailBy(this.id.value()));
        this.setBirthDate(null);
        this.setPromotionNotificationsAllowed(false);
        this.setAddress(Address.ANONYMOUS);
    }

    public void enablePromotionNotifications() {
        requireChangeable();
        this.setPromotionNotificationsAllowed(true);
    }

    public void disablePromotionNotifications() {
        requireChangeable();
        this.setPromotionNotificationsAllowed(false);
    }

    public void changeName(final FullName newName) {
        requireChangeable();
        this.setFullName(newName);
    }

    public void changeEmail(final Email newEmail) {
        requireChangeable();
        this.setEmail(newEmail);
    }

    public void changePhone(final Phone newPhone) {
        requireChangeable();
        this.setPhone(newPhone);
    }

    public void changeAddress(final Address newAddress) {
        requireChangeable();
        this.setAddress(newAddress);
    }

    public CustomerId id() {
        return id;
    }

    private void setId(final CustomerId id) {
        this.id = Objects.requireNonNull(id);
    }

    public FullName fullName() {
        return fullName;
    }

    private void setFullName(final FullName fullName) {
        this.fullName = Objects.requireNonNull(fullName, VALIDATION_ERROR_FULL_NAME_IS_NULL);
    }

    public BirthDate birthDate() {
        return birthDate;
    }

    private void setBirthDate(final BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public Email email() {
        return email;
    }

    private void setEmail(final Email email) {
        if (email == null) {
            throw new IllegalArgumentException(VALIDATION_ERROR_EMAIL_IS_INVALID);
        }
        this.email = email;
    }

    public Phone phone() {
        return phone;
    }

    private void setPhone(final Phone phone) {
        this.phone = Objects.requireNonNull(phone, VALIDATION_ERROR_INVALID_PHONE);
    }

    public Document document() {
        return this.document;
    }

    private void setDocument(final Document document) {
        this.document = Objects.requireNonNull(document, VALIDATION_ERROR_INVALID_DOCUMENT);
    }

    public Boolean isPromotionNotificationsAllowed() {
        return promotionNotificationsAllowed;
    }

    private void setPromotionNotificationsAllowed(final Boolean promotionNotificationsAllowed) {
        this.promotionNotificationsAllowed = Objects.requireNonNull(promotionNotificationsAllowed);
    }

    public Boolean isArchived() {
        return archived;
    }

    private void setArchived(final Boolean archived) {
        this.archived = Objects.requireNonNull(archived);
    }

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    private void setRegisteredAt(final OffsetDateTime registeredAt) {
        this.registeredAt = Objects.requireNonNull(registeredAt);
    }

    public OffsetDateTime archivedAt() {
        return archivedAt;
    }

    private void setArchivedAt(final OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    public LoyaltyPoints loyaltyPoints() {
        return loyaltyPoints;
    }

    private void setLoyaltyPoints(final LoyaltyPoints loyaltyPoints) {
        this.loyaltyPoints = Objects.requireNonNull(loyaltyPoints);
    }

    public Address address() {
        return address;
    }

    private void setAddress(Address address) {
        this.address = Objects.requireNonNull(address, VALIDATION_ERROR_INVALID_ADDRESS);
    }

    private void requireChangeable() {
        if (this.isArchived()) {
            throw new CustomerArchivedException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.id(), customer.id());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id());
    }
}
