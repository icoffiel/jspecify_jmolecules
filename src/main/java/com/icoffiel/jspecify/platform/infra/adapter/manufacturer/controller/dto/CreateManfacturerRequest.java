package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateManfacturerRequest(
        @NotBlank String name
) {
}
