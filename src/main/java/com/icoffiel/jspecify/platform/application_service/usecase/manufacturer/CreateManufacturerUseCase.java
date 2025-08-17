package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.port.ManufacturerRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateManufacturerUseCase {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerCreatedDto createManufacturer(CreateManufacturerCommand command) {
        Manufacturer savedManufacturer = manufacturerRepository.save(
                new Manufacturer(
                        command.name()
                )
        );

        return new ManufacturerCreatedDto(
                savedManufacturer.getId().id(),
                savedManufacturer.getName()
        );
    }
}
