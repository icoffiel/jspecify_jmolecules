package com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "platform")
public class PlatformEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @NotBlank
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    public PlatformEntity(String name, LocalDate releaseDate, String manufacturer) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }
}