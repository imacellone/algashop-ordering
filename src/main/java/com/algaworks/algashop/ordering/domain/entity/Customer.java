package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;

import java.time.OffsetDateTime;
import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;
import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL;

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

    public Customer(final CustomerId id,
                    final FullName fullName,
                    final BirthDate birthDate,
                    final Email email,
                    final Phone phone,
                    final Document document,
                    final Boolean promotionNotificationsAllowed,
                    final OffsetDateTime registeredAt) {
        this(id,
                fullName,
                birthDate,
                email,
                phone,
                document,
                promotionNotificationsAllowed,
                false,
                registeredAt,
                null,
                LoyaltyPoints.ZERO);
    }

    public Customer(final CustomerId id,
                    final FullName fullName,
                    final BirthDate birthDate,
                    final Email email,
                    final Phone phone,
                    final Document document,
                    final Boolean promotionNotificationsAllowed,
                    final Boolean archived,
                    final OffsetDateTime registeredAt,
                    final OffsetDateTime archivedAt,
                    final LoyaltyPoints loyaltyPoints) {
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

    public CustomerId id() {
        return id;
    }

    private void setId(final CustomerId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public FullName fullName() {
        return fullName;
    }

    private void setFullName(final FullName fullName) {
        this.fullName = Objects.requireNonNull(fullName, VALIDATION_ERROR_FULLNAME_IS_NULL);
        ;
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
        Objects.requireNonNull(phone);
        this.phone = phone;
    }

    public Document document() {
        return this.document;
    }

    private void setDocument(final Document document) {
        Objects.requireNonNull(document);
        this.document = document;
    }

    public Boolean isPromotionNotificationsAllowed() {
        return promotionNotificationsAllowed;
    }

    private void setPromotionNotificationsAllowed(final Boolean promotionNotificationsAllowed) {
        Objects.requireNonNull(promotionNotificationsAllowed);
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
    }

    public Boolean isArchived() {
        return archived;
    }

    private void setArchived(final Boolean archived) {
        Objects.requireNonNull(archived);
        this.archived = archived;
    }

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    private void setRegisteredAt(final OffsetDateTime registeredAt) {
        Objects.requireNonNull(registeredAt);
        this.registeredAt = registeredAt;
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
