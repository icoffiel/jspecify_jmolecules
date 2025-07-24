package com.icoffiel.jspecify.infra.adapter.controller.rest;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PlatformDto(
        @NotNull UUID id,
        @NotNull String name,
        @NotNull String releaseDate,
        @NotNull String manufacturer
) {
}
