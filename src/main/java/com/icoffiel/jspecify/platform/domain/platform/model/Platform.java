package com.icoffiel.jspecify.platform.domain.platform.model;

import com.icoffiel.jspecify.platform.domain.manufacturer.model.Manufacturer;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
public class Platform {
    private final PlatformId id;
    private final String name;
    private final LocalDate releaseDate;
    private final Manufacturer manufacturer;

    public Platform(String name, LocalDate releaseDate, Manufacturer manufacturer) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(releaseDate, "releaseDate must not be null");
        Assert.notNull(manufacturer, "manufacturerId must not be null");

        this.id = new PlatformId();
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public Platform(PlatformId id, String name, LocalDate releaseDate, Manufacturer manufacturer) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(releaseDate, "releaseDate must not be null");
        Assert.notNull(manufacturer, "manufacturerId must not be null");

        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }
}
