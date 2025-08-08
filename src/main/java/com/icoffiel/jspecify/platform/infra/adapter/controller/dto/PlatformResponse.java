package com.icoffiel.jspecify.platform.infra.adapter.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PlatformResponse(
        UUID id,
        String name,
        LocalDate releaseDate,
        String manufacturer
) {
}
