package com.icoffiel.jspecify.infra.adapter.persistence.jpa;

import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntity;
import org.instancio.InstancioApi;

import java.time.LocalDate;
import java.util.UUID;

import static org.instancio.Instancio.of;
import static org.instancio.Select.field;

public class PlatformEntityBuilder {
    private final InstancioApi<PlatformEntity> builder = of(PlatformEntity.class);

    public static PlatformEntityBuilder aPlatformEntity() {
        return new PlatformEntityBuilder();
    }

    public PlatformEntityBuilder withId( UUID id) {
        builder.set(field(PlatformEntity::getId), id);
        return this;
    }

    public PlatformEntityBuilder withName(String name) {
        builder.set(field(PlatformEntity::getName), name);
        return this;
    }

    public PlatformEntityBuilder withReleaseDate(LocalDate releaseDate) {
        builder.set(field(PlatformEntity::getReleaseDate), releaseDate);
        return this;
    }

    public PlatformEntityBuilder withManufacturer(String manufacturer) {
        builder.set(field(PlatformEntity::getManufacturer), manufacturer);
        return this;
    }

    public PlatformEntity build() {
        return builder.create();
    }
}
