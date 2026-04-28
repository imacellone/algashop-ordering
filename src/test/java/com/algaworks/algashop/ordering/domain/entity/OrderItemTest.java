package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.valueobject.Money;
import com.algaworks.algashop.ordering.domain.valueobject.ProductName;
import com.algaworks.algashop.ordering.domain.valueobject.Quantity;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.domain.valueobject.id.ProductId;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void shouldGenerate() {
        final OrderItem brandNew = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Product Name"))
                .price(Money.ZERO)
                .quantity(Quantity.ONE)
                .build();
    }

}