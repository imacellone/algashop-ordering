package com.algaworks.algashop.ordering.domain.valueobject.id;

import com.algaworks.algashop.ordering.utility.IdGenerator;
import io.hypersistence.tsid.TSID;

import java.util.Objects;

public record OrderItemId(TSID value) {

    public OrderItemId {
        Objects.requireNonNull(value);
    }

    public OrderItemId() {
        this(IdGenerator.generateTSID());
    }

    public OrderItemId(final long value) {
        this(TSID.from(value));
    }

    public OrderItemId(final String value) {
        this(TSID.from(Objects.requireNonNull(value)));
    }
}
