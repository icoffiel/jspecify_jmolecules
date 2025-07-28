package com.icoffiel.jspecify.infra.adapter.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PlatformCreatedResponse(
        @NotNull UUID id,
        @NotNull String name,
        @NotNull LocalDate releaseDate,
        @NotNull String manufacturer
) {
}
