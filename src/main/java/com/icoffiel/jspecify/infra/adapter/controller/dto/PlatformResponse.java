package com.icoffiel.jspecify.infra.adapter.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PlatformResponse(
        UUID id,
        String name,
        LocalDate releaseDate,
        String manufacturer
) {
}
