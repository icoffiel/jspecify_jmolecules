package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.port.ManufacturerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllManufacturersUseCase {
    private final ManufacturerRepository manufacturerRepository;

    public List<ManufacturerDto> getAllManufacturers() {
        return manufacturerRepository.findAll().stream()
                .map(GetAllManufacturersUseCase::toManufacturerDto)
                .toList();
    }

    private static ManufacturerDto toManufacturerDto(Manufacturer manufacturer) {
        return new ManufacturerDto(
                manufacturer.getId().id(),
                manufacturer.getName()
        );
    }
}
