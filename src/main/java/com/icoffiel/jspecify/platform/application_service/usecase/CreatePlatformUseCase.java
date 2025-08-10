package com.icoffiel.jspecify.platform.application_service.usecase;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;

@UseCase
public class CreatePlatformUseCase {
    private final PlatformRepository platformRepository;

    public CreatePlatformUseCase(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public PlatformCreatedDto createPlatform(CreatePlatformCommand command) {
        Platform savedPlatform = platformRepository.save(
                new Platform(
                        command.name(),
                        command.releaseDate(),
                        command.manufacturer()
                )
        );

        return new PlatformCreatedDto(
                savedPlatform.getId().id(),
                savedPlatform.getName(),
                savedPlatform.getReleaseDate(),
                savedPlatform.getManufacturer()
        );
    }
}
