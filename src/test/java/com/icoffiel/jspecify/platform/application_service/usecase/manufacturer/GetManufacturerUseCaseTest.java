package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntity;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityRepository;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class GetManufacturerUseCaseTest {
    @Autowired
    private GetManufacturerUseCase getManufacturerUseCase;

    @Autowired
    private ManufacturerEntityRepository entityRepository;

    private List<ManufacturerEntity> savedEntities;

    @BeforeEach
    void setUp() {
        entityRepository.deleteAll();

        savedEntities = List.of(
                ManufacturerEntityBuilder.aManufacturerEntity()
                        .withId(null)
                        .build(),
                ManufacturerEntityBuilder.aManufacturerEntity()
                        .withId(null)
                        .build()
        );

        entityRepository.saveAll(
                savedEntities
        );
    }

    @AfterEach
    void tearDown() {
        entityRepository.deleteAll();
    }

    @Test
    void can_get_manufacturer() {
        ManufacturerEntity manufacturer = savedEntities.getFirst();

        ManufacturerDto manufacturerDto = getManufacturerUseCase.getManufacturer(manufacturer.getId());

        assertThat(manufacturerDto.id()).isNotNull();
        assertThat(manufacturerDto.name()).isEqualTo(manufacturer.getName());
    }

    @Test
    void no_manufacturer_found() {
        UUID id = UUID.randomUUID();
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> getManufacturerUseCase.getManufacturer(id)
        );

        assertThat(exception.getMessage()).isEqualTo("Resource not found: " + id);
    }
}