package com.icoffiel.jspecify.platform.application_service.usecase;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.persistence.jpa.PlatformEntityBuilder;
import com.icoffiel.jspecify.platform.application_service.usecase.platform.GetAllPlatformsUseCase;
import com.icoffiel.jspecify.platform.application_service.usecase.platform.PlatformDto;
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

    @BeforeEach
    void setUp() {
        entityRepository.deleteAll();

        entityRepository.saveAll(
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
        entityRepository.deleteAll();
    }

    @Test
    void can_get_all_platforms() {
        List<PlatformDto> allPlatforms = useCase.getAllPlatforms();

        assertThat(allPlatforms, hasSize(2));
    }
}