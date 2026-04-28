package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.Money;
import com.algaworks.algashop.ordering.domain.valueobject.ProductName;
import com.algaworks.algashop.ordering.domain.valueobject.Quantity;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderItemId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @EqualsAndHashCode.Include
    private OrderItemId id;
    private OrderId orderId;
    private ProductId productId;
    private ProductName productName;
    private Money price;
    private Quantity quantity;
    private Money totalAmount;

    @Builder(builderClassName = "ExistingOrderItem", builderMethodName = "existing")
    private OrderItem(final OrderItemId id,
                     final OrderId orderId,
                     final ProductId productId,
                     final ProductName productName,
                     final Money price,
                     final Quantity quantity,
                     final Money totalAmount) {
        setId(id);
        setOrderId(orderId);
        setProductId(productId);
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
        setTotalAmount(totalAmount);
    }

    @Builder(builderClassName = "BrandNewOrderItem", builderMethodName = "brandNew")
    private static OrderItem OrderItem(
            final OrderId orderId,
            final ProductId productId,
            final ProductName productName,
            final Money price,
            final Quantity quantity) {

        final OrderItem orderItem = new OrderItem(new OrderItemId(),
                orderId,
                productId,
                productName,
                price,
                quantity,
                Money.ZERO
        );
        orderItem.recalculateTotals();
        return orderItem;
    }

    public OrderItemId id() {
        return id;
    }

    private void setId(final OrderItemId id) {
        this.id = Objects.requireNonNull(id);
    }

    public OrderId orderId() {
        return orderId;
    }

    private void setOrderId(final OrderId orderId) {
        this.orderId = Objects.requireNonNull(orderId);
    }

    public ProductId productId() {
        return productId;
    }

    private void setProductId(final ProductId productId) {
        this.productId = Objects.requireNonNull(productId);
    }

    public ProductName productName() {
        return productName;
    }

    private void setProductName(final ProductName productName) {
        this.productName = Objects.requireNonNull(productName);
    }

    public Money price() {
        return price;
    }

    private void setPrice(final Money price) {
        this.price = Objects.requireNonNull(price);
    }

    public Quantity quantity() {
        return quantity;
    }

    private void setQuantity(final Quantity quantity) {
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Money totalAmount() {
        return totalAmount;
    }

    private void setTotalAmount(final Money totalAmount) {
        this.totalAmount = Objects.requireNonNull(totalAmount);
    }

    private void recalculateTotals() {
        this.setTotalAmount(this.price().multiply(this.quantity()));
    }
}
