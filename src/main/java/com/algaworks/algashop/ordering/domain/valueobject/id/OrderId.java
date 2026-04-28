package com.algaworks.algashop.ordering.domain.valueobject.id;

import com.algaworks.algashop.ordering.utility.IdGenerator;
import io.hypersistence.tsid.TSID;

import java.util.Objects;

public record OrderId(TSID value) {

    public OrderId {
        Objects.requireNonNull(value);
    }

    public OrderId() {
        this(IdGenerator.generateTSID());
    }

    public OrderId(final long value) {
        this(TSID.from(value));
    }

    public OrderId(final String value) {
        this(TSID.from(Objects.requireNonNull(value)));
    }
}
