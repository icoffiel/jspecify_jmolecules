package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ManufacturerEntityRepository extends ListCrudRepository<ManufacturerEntity, UUID> {
}
