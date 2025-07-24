package com.icoffiel.jspecify.application_service;

import com.icoffiel.jspecify.infra.adapter.controller.rest.CreatePlatformDto;
import com.icoffiel.jspecify.infra.adapter.controller.rest.PlatformDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlatformService {
    public List<PlatformDto> getPlatforms() {
        return List.of();
    }

    public PlatformDto getPlatform(UUID id) {
        return new PlatformDto(
                id,
                "Xbox Series S",
                "2020-11-12",
                "Microsoft"
        );
    }

    public PlatformDto createPlatform(CreatePlatformDto createPlatformRequestDto) {
        return new PlatformDto(
                UUID.randomUUID(),
                createPlatformRequestDto.name(),
                createPlatformRequestDto.releaseDate(),
                createPlatformRequestDto.manufacturer()
        );
    }

    public void deletePlatform(UUID id) {

    }

    public PlatformDto updatePlatform(UUID id, PlatformDto platformDto) {
        return platformDto;
    }
}
