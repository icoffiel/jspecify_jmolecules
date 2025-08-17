package com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PlatformEntityRepository extends ListCrudRepository<PlatformEntity, UUID> {
}
