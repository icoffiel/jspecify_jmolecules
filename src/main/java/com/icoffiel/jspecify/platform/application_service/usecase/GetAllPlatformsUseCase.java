package com.icoffiel.jspecify.platform.application_service.usecase;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.model.Platform;
import com.icoffiel.jspecify.platform.domain.port.PlatformRepository;

import java.util.List;

@UseCase
public class GetAllPlatformsUseCase {
    private final PlatformRepository platformRepository;

    public GetAllPlatformsUseCase(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<PlatformDto> getAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(GetAllPlatformsUseCase::toPlatformDto)
                .toList();
    }

    private static PlatformDto toPlatformDto(Platform platform) {
        return new PlatformDto(platform.getId().id(), platform.getName(), platform.getReleaseDate(), platform.getManufacturer());
    }
}
