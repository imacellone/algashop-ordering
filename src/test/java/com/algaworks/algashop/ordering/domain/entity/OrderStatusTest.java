package com.algaworks.algashop.ordering.domain.entity;

import org.junit.jupiter.api.Test;

import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.CANCELED;
import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.DRAFT;
import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.PAID;
import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.PLACED;
import static com.algaworks.algashop.ordering.domain.entity.OrderStatus.READY;
import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusTest {

    @Test
    void canChangeTo() {
        assertThat(DRAFT.canChangeTo(PLACED)).isTrue();
        assertThat(PLACED.canChangeTo(PAID)).isTrue();
        assertThat(PAID.canChangeTo(READY)).isTrue();

        assertThat(DRAFT.canChangeTo(CANCELED)).isTrue();
        assertThat(PLACED.canChangeTo(CANCELED)).isTrue();
        assertThat(PAID.canChangeTo(CANCELED)).isTrue();
        assertThat(READY.canChangeTo(CANCELED)).isTrue();
    }

    @Test
    void canNotChangeTo() {
        assertThat(DRAFT.canNotChangeTo(DRAFT)).isTrue();
        assertThat(DRAFT.canNotChangeTo(PAID)).isTrue();
        assertThat(DRAFT.canNotChangeTo(READY)).isTrue();

        assertThat(PLACED.canNotChangeTo(PLACED)).isTrue();
        assertThat(PLACED.canNotChangeTo(READY)).isTrue();
        assertThat(PLACED.canNotChangeTo(DRAFT)).isTrue();

        assertThat(PAID.canNotChangeTo(PAID)).isTrue();
        assertThat(PAID.canNotChangeTo(DRAFT)).isTrue();
        assertThat(PAID.canNotChangeTo(PLACED)).isTrue();

        assertThat(CANCELED.canNotChangeTo(CANCELED)).isTrue();
        assertThat(CANCELED.canNotChangeTo(DRAFT)).isTrue();
        assertThat(CANCELED.canNotChangeTo(PLACED)).isTrue();
        assertThat(CANCELED.canNotChangeTo(PAID)).isTrue();
        assertThat(CANCELED.canNotChangeTo(READY)).isTrue();
    }

}