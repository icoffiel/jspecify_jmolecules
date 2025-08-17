package com.icoffiel.jspecify.infra.adapter.manufacturer.persistence.jpa;

import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntity;
import org.instancio.InstancioApi;

import java.util.UUID;

import static org.instancio.Instancio.of;
import static org.instancio.Select.field;

public class ManufacturerEntityBuilder {
    private final InstancioApi<ManufacturerEntity> builder = of(ManufacturerEntity.class);

    public static ManufacturerEntityBuilder aManufacturerEntity() {
        return new ManufacturerEntityBuilder();
    }

    public ManufacturerEntityBuilder withId( UUID id) {
        builder.set(field(ManufacturerEntity::getId), id);
        return this;
    }

    public ManufacturerEntityBuilder withName(String name) {
        builder.set(field(ManufacturerEntity::getName), name);
        return this;
    }

    public ManufacturerEntity build() {
        return builder.create();
    }
}
