package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.platform.persistence.jpa.PlatformEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntity;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntityRepository;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class GetPlatformUseCaseTest {
    @Autowired
    private GetPlatformUseCase getPlatformUseCase;

    @Autowired
    private PlatformEntityRepository platformEntityRepository;

    private List<PlatformEntity> savedPlatforms;

    @BeforeEach
    void setUp() {
        platformEntityRepository.deleteAll();
        savedPlatforms =platformEntityRepository.saveAll(
                List.of(
                        PlatformEntityBuilder.aPlatformEntity()
                                .withId(null)
                                .build(),
                        PlatformEntityBuilder.aPlatformEntity()
                                .withId(null)
                                .build()
                )
        );
    }

    @AfterEach
    void tearDown() {
        platformEntityRepository.deleteAll();
    }

    @Test
    void can_get_platform() {
        PlatformEntity aSavedPlatform = savedPlatforms.getFirst();

        PlatformDto platform = getPlatformUseCase.getPlatform(aSavedPlatform.getId());

        assertThat(platform, notNullValue());
        assertThat(platform.id(), equalTo(aSavedPlatform.getId()));
        assertThat(platform.name(), equalTo(aSavedPlatform.getName()));
        assertThat(platform.releaseDate(), equalTo(aSavedPlatform.getReleaseDate()));
        assertThat(platform.manufacturer(), equalTo(aSavedPlatform.getManufacturer()));
    }

    @Test
    void no_platform_found() {
        UUID id = UUID.randomUUID();
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> getPlatformUseCase.getPlatform(id)
        );

        assertThat(exception.getMessage(), equalTo("Resource not found: " + id));

    }
}