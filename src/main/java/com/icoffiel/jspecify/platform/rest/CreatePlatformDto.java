package com.icoffiel.jspecify.platform.rest;

import jakarta.validation.constraints.NotNull;

public record CreatePlatformDto(
        @NotNull String name,
        @NotNull String releaseDate,
        @NotNull String manufacturer
) {
}
