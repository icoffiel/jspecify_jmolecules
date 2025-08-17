package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.ManufacturerId;
import com.icoffiel.jspecify.platform.domain.manufacturer.port.ManufacturerRepository;
import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePlatformUseCase {
    private final PlatformRepository platformRepository;
    private final ManufacturerRepository manufacturerRepository;

    public PlatformCreatedDto createPlatform(CreatePlatformCommand command) {
        Manufacturer manufacturer = manufacturerRepository
                .findById(new ManufacturerId(command.manufacturerId()));

        Platform savedPlatform = platformRepository.save(
                new Platform(
                        command.name(),
                        command.releaseDate(),
                        manufacturer
                )
        );

        return new PlatformCreatedDto(
                savedPlatform.getId().id(),
                savedPlatform.getName(),
                savedPlatform.getReleaseDate(),
                savedPlatform.getManufacturer().getId().id()
        );
    }
}
