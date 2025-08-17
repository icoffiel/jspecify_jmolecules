package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntity;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.persistence.jpa.ManufacturerEntityRepository;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntityRepository;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class CreatePlatformUseCaseTest {
    @Autowired
    private CreatePlatformUseCase useCase;

    @Autowired
    private ManufacturerEntityRepository manufacturerEntityRepository;

    @Autowired
    private PlatformEntityRepository platformEntityRepository;

    private ManufacturerEntity savedManufacturer;

    @BeforeEach
    void setUp() {
        platformEntityRepository.deleteAll();
        manufacturerEntityRepository.deleteAll();

        savedManufacturer = manufacturerEntityRepository.save(
                ManufacturerEntityBuilder.aManufacturerEntity()
                        .withId(null)
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        platformEntityRepository.deleteAll();
        manufacturerEntityRepository.deleteAll();
    }

    @Test
    void can_create_platform() {
        // given
        CreatePlatformCommand command = new CreatePlatformCommand(
                "platform",
                LocalDate.now(),
                savedManufacturer.getId()
        );

        // when
        PlatformCreatedDto result = useCase.createPlatform(command);

        // then
        assertNotNull(result);
        assertEquals(command.name(), result.name());
        assertEquals(command.releaseDate(), result.releaseDate());
        assertEquals(command.manufacturerId(), result.manufacturerId());
    }

    @Test
    void cannot_create_platform_with_non_existent_manufacturer() {
        UUID id = UUID.randomUUID();
        CreatePlatformCommand command = new CreatePlatformCommand(
                "platform",
                LocalDate.now(),
                id
        );

        NotFoundException exception = assertThrows(NotFoundException.class, () -> useCase.createPlatform(command));

        assertThat(exception.getMessage(), equalTo("Resource not found: " + id));
    }
}