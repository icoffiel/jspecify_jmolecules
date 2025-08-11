package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePlatformUseCase {
    private final PlatformRepository platformRepository;

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
