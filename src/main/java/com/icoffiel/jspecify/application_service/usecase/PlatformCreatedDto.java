package com.icoffiel.jspecify.application_service.usecase;

import java.time.LocalDate;
import java.util.UUID;

public record PlatformCreatedDto(
        UUID id,
        String name,
        LocalDate releaseDate,
        String manufacturer
) {
}
