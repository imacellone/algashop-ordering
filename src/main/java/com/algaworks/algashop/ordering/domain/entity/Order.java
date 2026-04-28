package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import com.algaworks.algashop.ordering.domain.valueobject.*;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.DRAFT;
import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.PLACED;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @EqualsAndHashCode.Include
    private OrderId id;
    private CustomerId customerId;
    private Money totalAmount;
    private Quantity totalItems;
    private OffsetDateTime placedAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime canceledAt;
    private OffsetDateTime readyAt;
    private BillingInfo billingInfo;
    private ShippingInfo shippingInfo;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private Money shippingCost;
    private LocalDate expectedDeliveryDate;
    private Set<OrderItem> items;

    @Builder(builderClassName = "ExistingOrderBuilder", builderMethodName = "existing")
    private Order(final OrderId id,
                 final CustomerId customerId,
                 final Money totalAmount,
                 final Quantity totalItems,
                 final OffsetDateTime placedAt,
                 final OffsetDateTime paidAt,
                 final OffsetDateTime canceledAt,
                 final OffsetDateTime readyAt,
                 final BillingInfo billingInfo,
                 final ShippingInfo shippingInfo,
                 final OrderStatus status,
                 final PaymentMethod paymentMethod,
                 final Money shippingCost,
                 final LocalDate expectedDeliveryDate,
                 final Set<OrderItem> items) {
        setId(id);
        setCustomerId(customerId);
        setTotalAmount(totalAmount);
        setTotalItems(totalItems);
        setPlacedAt(placedAt);
        setPaidAt(paidAt);
        setCanceledAt(canceledAt);
        setReadyAt(readyAt);
        setBillingInfo(billingInfo);
        setShippingInfo(shippingInfo);
        setStatus(status);
        setPaymentMethod(paymentMethod);
        setShippingCost(shippingCost);
        setExpectedDeliveryDate(expectedDeliveryDate);
        setItems(items);
    }

    public static Order draft(final CustomerId customerId) {
        return new Order(
                new OrderId(),
                customerId,
                Money.ZERO,
                Quantity.ZERO,
                null,
                null,
                null,
                null,
                null,
                null,
                OrderStatus.DRAFT,
                null,
                Money.ZERO,
                null,
                new HashSet<>()
        );
    }

    public void addItem(final ProductId productId,
                        final ProductName productName,
                        final Money price,
                        final Quantity quantity) {

        final OrderItem orderItem = OrderItem.brandNew()
                .orderId(this.id())
                .productId(productId)
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .build();

        this.items.add(orderItem);
        this.recalculateTotals();
    }

    public void place() {
        // TODO business rules
        this.changeStatus(PLACED);
    }

    public boolean isDraft() {
        return DRAFT.equals(this.status());
    }

    public boolean isPlaced() {
        return PLACED.equals(this.status());
    }

    private void changeStatus(OrderStatus newStatus) {
        Objects.requireNonNull(newStatus);

        if (this.status().canNotChangeTo(newStatus)) {
            throw new OrderStatusCannotBeChangedException(this.id(), this.status(), newStatus);
        }
        setStatus(newStatus);

    }

    public OrderId id() {
        return id;
    }

    private void setId(final OrderId id) {
        this.id = Objects.requireNonNull(id);
    }

    public CustomerId customerId() {
        return customerId;
    }

    private void setCustomerId(final CustomerId customerId) {
        this.customerId = Objects.requireNonNull(customerId);
    }

    public Money totalAmount() {
        return totalAmount;
    }

    private void setTotalAmount(final Money totalAmount) {
        this.totalAmount = Objects.requireNonNull(totalAmount);
    }

    public Quantity totalItems() {
        return totalItems;
    }

    private void setTotalItems(final Quantity totalItems) {
        this.totalItems = Objects.requireNonNull(totalItems);
    }

    public OffsetDateTime placedAt() {
        return placedAt;
    }

    private void setPlacedAt(final OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public OffsetDateTime paidAt() {
        return paidAt;
    }

    private void setPaidAt(final OffsetDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public OffsetDateTime canceledAt() {
        return canceledAt;
    }

    private void setCanceledAt(final OffsetDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public OffsetDateTime readyAt() {
        return readyAt;
    }

    private void setReadyAt(final OffsetDateTime readyAt) {
        this.readyAt = readyAt;
    }

    public BillingInfo billingInfo() {
        return billingInfo;
    }

    private void setBillingInfo(final BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public ShippingInfo shippingInfo() {
        return shippingInfo;
    }

    private void setShippingInfo(final ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public OrderStatus status() {
        return status;
    }

    private void setStatus(final OrderStatus status) {
        this.status = Objects.requireNonNull(status);
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    private void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Money shippingCost() {
        return shippingCost;
    }

    private void setShippingCost(final Money shippingCost) {
        this.shippingCost = shippingCost;
    }

    public LocalDate expectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    private void setExpectedDeliveryDate(final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Set<OrderItem> items() {
        return Collections.unmodifiableSet(this.items);
    }

    private void setItems(final Set<OrderItem> items) {
        this.items = Objects.requireNonNull(items);
    }

    private void recalculateTotals() {
        final BigDecimal totalItemsAmount = this.items().stream()
                .map(OrderItem::totalAmount)
                .map(Money::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal shippingCost = Optional.ofNullable(this.shippingCost())
                .map(Money::value)
                .orElse(BigDecimal.ZERO);

        final BigDecimal totalAmount = totalItemsAmount.add(shippingCost);

        this.setTotalAmount(new Money(totalAmount));

        final Long totalQuantity = this.items().stream()
                .map(OrderItem::quantity)
                .map(Quantity::value)
                .reduce(0L, Long::sum);

        this.setTotalItems(new Quantity(totalQuantity));
    }
}
