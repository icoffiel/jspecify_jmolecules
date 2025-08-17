package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa;

import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import com.icoffiel.jspecify.platform.domain.manufacturer.model.ManufacturerId;
import com.icoffiel.jspecify.platform.domain.manufacturer.port.ManufacturerRepository;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping
public class JpaManufacturerRepository implements ManufacturerRepository {
    private final ManufacturerEntityRepository manufacturerEntityRepository;

    public JpaManufacturerRepository(ManufacturerEntityRepository manufacturerEntityRepository) {
        this.manufacturerEntityRepository = manufacturerEntityRepository;
    }

    @Override
    public @NonNull List<Manufacturer> findAll() {
        return manufacturerEntityRepository.findAll().stream()
                .map(JpaManufacturerRepository::mapToManufacturer)
                .toList();
    }

    @Override
    public @NonNull Manufacturer findById(@NonNull ManufacturerId id) {
        return manufacturerEntityRepository.findById(id.id())
                .map(JpaManufacturerRepository::mapToManufacturer)
                .orElseThrow(() -> new NotFoundException(id.id()));
    }

    @Override
    public @NonNull Manufacturer save(@NonNull Manufacturer manufacturer) {
        ManufacturerEntity savedEntity = manufacturerEntityRepository.save(new ManufacturerEntity(manufacturer.getName()));

        return mapToManufacturer(savedEntity);
    }

    private static Manufacturer mapToManufacturer(ManufacturerEntity entity) {
        return new Manufacturer(
                new ManufacturerId(entity.getId()),
                entity.getName()
        );
    }
}
