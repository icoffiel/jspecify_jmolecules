package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import java.time.LocalDate;
import java.util.UUID;

public record PlatformCreatedDto(
        UUID id,
        String name,
        LocalDate releaseDate,
        UUID manufacturerId
) {
}
