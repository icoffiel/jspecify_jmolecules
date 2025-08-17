package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllPlatformsUseCase {
    private final PlatformRepository platformRepository;

    public List<PlatformDto> getAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(GetAllPlatformsUseCase::toPlatformDto)
                .toList();
    }

    private static PlatformDto toPlatformDto(Platform platform) {
        return new PlatformDto(
                platform.getId().id(),
                platform.getName(),
                platform.getReleaseDate(),
                platform.getManufacturer().getId().id()
        );
    }
}
