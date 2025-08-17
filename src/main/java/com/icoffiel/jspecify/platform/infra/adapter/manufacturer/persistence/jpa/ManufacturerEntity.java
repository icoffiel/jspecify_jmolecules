package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "manufacturerId")
public class ManufacturerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public ManufacturerEntity(String name) {
        Assert.notNull(name, "name must not be null");
        this.name = name;
    }

    public ManufacturerEntity(UUID id, String name) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");
        this.id = id;
        this.name = name;
    }

}