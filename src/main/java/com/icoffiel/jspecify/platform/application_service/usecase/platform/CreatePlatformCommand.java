package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import java.time.LocalDate;
import java.util.UUID;

public record CreatePlatformCommand(
        String name,
        LocalDate releaseDate,
        UUID manufacturerId
) {
}
