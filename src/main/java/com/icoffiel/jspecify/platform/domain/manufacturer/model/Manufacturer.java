package com.icoffiel.jspecify.platform.domain.manufacturer.model;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Manufacturer {
    private final ManufacturerId id;
    private final String name;

    public Manufacturer(String name) {
        Assert.notNull(name, "name must not be null");

        this.id = new ManufacturerId();
        this.name = name;
    }

    public Manufacturer(ManufacturerId id, String name) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");

        this.id = id;
        this.name = name;
    }
}
