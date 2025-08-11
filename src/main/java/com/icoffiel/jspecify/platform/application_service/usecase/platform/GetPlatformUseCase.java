package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.model.PlatformId;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;

import java.util.UUID;

@UseCase
public class GetPlatformUseCase {
    private final PlatformRepository platformRepository;

    public GetPlatformUseCase(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public PlatformDto getPlatform(UUID id) {
        return toPlatformDto(
                platformRepository.findById(
                        new PlatformId(id)
                )
        );
    }

    private static PlatformDto toPlatformDto(Platform platform) {
        return new PlatformDto(
                platform.getId().id(),
                platform.getName(),
                platform.getReleaseDate(),
                platform.getManufacturer()
        );
    }
}
