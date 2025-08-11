package com.icoffiel.jspecify.platform.domain.manufacturer.model;

import org.springframework.util.Assert;

import java.util.UUID;

public record ManufacturerId(UUID id) {
    public ManufacturerId {
        Assert.notNull(id, "id must not be null");
    }

    public ManufacturerId() {
        this(UUID.randomUUID());
    }
}
