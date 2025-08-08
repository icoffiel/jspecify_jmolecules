package com.icoffiel.jspecify.platform.domain.model;

import org.springframework.util.Assert;

import java.util.UUID;

public record PlatformId (UUID id) {
    public PlatformId {
        Assert.notNull(id, "id must not be null");
    }

    public PlatformId() {
        this(UUID.randomUUID());
    }
}
