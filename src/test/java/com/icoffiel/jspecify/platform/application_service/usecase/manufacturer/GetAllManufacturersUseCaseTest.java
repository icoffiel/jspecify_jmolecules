package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntity;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class GetAllManufacturersUseCaseTest {
    @Autowired
    private GetAllManufacturersUseCase useCase;

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
    void can_get_all_manufacturers() {
        List<ManufacturerDto> allManufacturers = useCase.getAllManufacturers();

        List<String> names = savedEntities.stream()
                .map(ManufacturerEntity::getName)
                .toList();

        assertThat(allManufacturers)
                .extracting(ManufacturerDto::name)
                .containsExactlyInAnyOrderElementsOf(names);
    }
}