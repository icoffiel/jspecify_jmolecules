package com.icoffiel.jspecify.platform.domain.model;

import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.model.PlatformId;
import org.instancio.Instancio;
import org.instancio.InstancioApi;

import java.time.LocalDate;

import static org.instancio.Select.field;

public class PlatformBuilder {
    private final InstancioApi<Platform> builder = Instancio.of(Platform.class);

    public static PlatformBuilder aPlatform() {
        return new PlatformBuilder();
    }

    public PlatformBuilder withId(PlatformId id) {
        builder.set(field(Platform::getId), id);
        return this;
    }

    public PlatformBuilder withName(String name) {
        builder.set(field(Platform::getName), name);
        return this;
    }

    public PlatformBuilder withReleaseDate(LocalDate releaseDate) {
        builder.set(field(Platform::getReleaseDate), releaseDate);
        return this;
    }

    public PlatformBuilder withReleaseDate(String releaseDate) {
        builder.set(field(Platform::getReleaseDate), LocalDate.parse(releaseDate));
        return this;
    }

    public PlatformBuilder withManufacturer(String manufacturer) {
        builder.set(field(Platform::getManufacturer), manufacturer);
        return this;
    }

    public Platform build() {
        return builder.create();
    }

}
