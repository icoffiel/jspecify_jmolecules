package com.icoffiel.jspecify.platform.domain.model;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Getter
public class Platform {
    private PlatformId id;
    private String name;
    private LocalDate releaseDate;
    private String manufacturer;

    public Platform(String name, LocalDate releaseDate, String manufacturer) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(releaseDate, "releaseDate must not be null");
        Assert.notNull(manufacturer, "manufacturer must not be null");

        this.id = new PlatformId();
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public Platform(PlatformId id, String name, LocalDate releaseDate, String manufacturer) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(releaseDate, "releaseDate must not be null");
        Assert.notNull(manufacturer, "manufacturer must not be null");

        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }
}
