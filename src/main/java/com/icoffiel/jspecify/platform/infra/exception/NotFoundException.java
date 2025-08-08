package com.icoffiel.jspecify.platform.infra.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NotFoundException extends RuntimeException {
    private final UUID id;

    public NotFoundException(UUID id) {
        super("Resource not found: " + id);
        this.id = id;
    }
}
