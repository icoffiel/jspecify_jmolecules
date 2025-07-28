package com.icoffiel.jspecify.infra.adapter.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePlatformRequest(
        @NotNull String name,
        @NotNull LocalDate releaseDate,
        @NotNull String manufacturer
) {
}
