package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import com.algaworks.algashop.ordering.domain.valueobject.Money;
import com.algaworks.algashop.ordering.domain.valueobject.ProductName;
import com.algaworks.algashop.ordering.domain.valueobject.Quantity;
import com.algaworks.algashop.ordering.domain.valueobject.id.CustomerId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.RoundingMode.HALF_EVEN;
import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    void shouldGenerate() {
        final Order draft = Order.draft(new CustomerId());
    }

    @Test
    void shouldAddItem() {
        final Order givenOrder = Order.draft(new CustomerId());
        final ProductId givenProductId = new ProductId();
        final ProductName givenProductName = new ProductName("Product Name");
        final Money givenPrice = new Money("100");
        final Quantity givenQuantity = Quantity.ONE;

        givenOrder.addItem(givenProductId,
                givenProductName,
                givenPrice,
                givenQuantity);

        final Set<OrderItem> actualItems = givenOrder.items();

        assertThat(actualItems).isNotNull();
        assertThat(actualItems.size()).isEqualTo(1);
        assertWith(actualItems.iterator().next(),
                actualItem -> assertThat(actualItem.orderId()).isEqualTo(givenOrder.id()),
                actualItem -> assertThat(actualItem.id()).isNotNull(),
                actualItem -> assertThat(actualItem.productName()).isEqualTo(givenProductName),
                actualItem -> assertThat(actualItem.price()).isEqualTo(givenPrice),
                actualItem -> assertThat(actualItem.quantity()).isEqualTo(givenQuantity)
        );
    }

    @Test
    void shouldReturnUnmodifiableItemsSet() {
        final Order givenOrder = Order.draft(new CustomerId());
        final ProductId givenProductId = new ProductId();
        final ProductName givenProductName = new ProductName("Product Name");
        final Money givenPrice = new Money("100");
        final Quantity givenQuantity = Quantity.ONE;

        givenOrder.addItem(givenProductId,
                givenProductName,
                givenPrice,
                givenQuantity);

        final Set<OrderItem> actualOrderItemSet = givenOrder.items();
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(actualOrderItemSet::clear);
    }

    @Test
    void shouldCalculateTotals() {
        final Order givenOrder = Order.draft(new CustomerId());

        givenOrder.addItem(new ProductId(),
                new ProductName("Mouse"),
                new Money("100"),
                Quantity.ONE);

        assertWith(givenOrder,
                o -> assertThat(o.totalAmount().value()).isEqualTo(BigDecimal.valueOf(100.0).setScale(2, HALF_EVEN)),
                o -> assertThat(o.totalItems().value()).isEqualTo(1)
        );

        givenOrder.addItem(new ProductId(),
                new ProductName("Keyboard"),
                new Money("150"),
                Quantity.TWO);

        assertWith(givenOrder,
                o -> assertThat(o.totalAmount().value()).isEqualTo(BigDecimal.valueOf(400.0).setScale(2, HALF_EVEN)),
                o -> assertThat(o.totalItems().value()).isEqualTo(3)
        );
    }

    @Test
    void givenDraftOrder_whenPlace_shouldChangeToPlaced() {
        final Order givenOrder = Order.draft(new CustomerId());
        givenOrder.place();
        assertThat(givenOrder.isPlaced()).isTrue();
    }

    @Test
    void givenPlacedOrder_whenPlace_shouldThrowException() {
        final Order givenOrder = Order.draft(new CustomerId());
        givenOrder.place();
        assertThatExceptionOfType(OrderStatusCannotBeChangedException.class).isThrownBy(givenOrder::place);
    }

}