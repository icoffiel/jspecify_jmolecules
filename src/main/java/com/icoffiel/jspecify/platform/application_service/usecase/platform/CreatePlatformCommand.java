package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import java.time.LocalDate;

public record CreatePlatformCommand(
        String name,
        LocalDate releaseDate,
        String manufacturer
) {
}
