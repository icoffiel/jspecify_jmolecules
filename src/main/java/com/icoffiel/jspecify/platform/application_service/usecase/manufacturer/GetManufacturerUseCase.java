package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.ManufacturerId;
import com.icoffiel.jspecify.platform.domain.manufacturer.port.ManufacturerRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetManufacturerUseCase {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerDto getManufacturer(UUID id) {
        return toManufacturerDto(
                manufacturerRepository.findById(
                        new ManufacturerId(id)
                )
        );
    }

    private static ManufacturerDto toManufacturerDto(Manufacturer manufacturer) {
        return new ManufacturerDto(
                manufacturer.getId().id(),
                manufacturer.getName()
        );
    }
}
