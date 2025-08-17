package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller.dto;

import java.util.UUID;

public record ManufacturerCreatedResponse(
        UUID id,
        String name
) {
}
