package com.icoffiel.jspecify.platform.infra.adapter.platform.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreatePlatformRequest(
        @NotBlank String name,
        @NotNull LocalDate releaseDate,
        @NotNull UUID manufacturer
) {
}
