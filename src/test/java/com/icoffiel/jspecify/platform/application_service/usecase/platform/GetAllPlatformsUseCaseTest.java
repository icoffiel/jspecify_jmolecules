package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityBuilder;
import com.icoffiel.jspecify.infra.adapter.platform.persistence.jpa.PlatformEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntity;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityRepository;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class GetAllPlatformsUseCaseTest {

    @Autowired
    GetAllPlatformsUseCase useCase;

    @Autowired
    PlatformEntityRepository entityRepository;

    @Autowired
    ManufacturerEntityRepository manufacturerRepository;

    @BeforeEach
    void setUp() {
        entityRepository.deleteAll();
        manufacturerRepository.deleteAll();

        ManufacturerEntity savedManufacturer = manufacturerRepository.save(
                ManufacturerEntityBuilder.aManufacturerEntity()
                        .withId(null)
                        .build()
        );

        entityRepository.saveAll(
                List.of(
                        PlatformEntityBuilder.aPlatformEntity()
                                .withId(null)
                                .withManufacturer(savedManufacturer)
                                .build(),
                        PlatformEntityBuilder.aPlatformEntity()
                                .withId(null)
                                .withManufacturer(savedManufacturer)
                                .build()
                )
        );
    }

    @AfterEach
    void tearDown() {
        entityRepository.deleteAll();
    }

    @Test
    void can_get_all_platforms() {
        List<PlatformDto> allPlatforms = useCase.getAllPlatforms();

        assertThat(allPlatforms, hasSize(2));
    }
}