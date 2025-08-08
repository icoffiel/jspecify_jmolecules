package com.icoffiel.jspecify.platform.infra.adapter.persistence.jpa;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.persistence.jpa.PlatformEntityBuilder;
import com.icoffiel.jspecify.platform.domain.model.Platform;
import com.icoffiel.jspecify.platform.domain.model.PlatformBuilder;
import com.icoffiel.jspecify.platform.domain.model.PlatformId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@Import({TestcontainersConfiguration.class, JpaPlatformRepository.class})
class JpaPlatformRepositoryTest {
    @Autowired
    private JpaPlatformRepository repository;

    @Autowired
    private PlatformEntityRepository platformEntityRepository;

    @Test
    void can_delete_by_id() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = platformEntityRepository.save(platformEntity);

        repository.deleteById(new PlatformId(savedPlatform.getId()));

        PlatformEntity foundPlatform = platformEntityRepository
                .findById(savedPlatform.getId())
                .orElse(null);

        assertThat(foundPlatform, nullValue());
    }

    @Test
    void can_find_all_platforms() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = platformEntityRepository.save(platformEntity);

        List<Platform> platforms = repository.findAll();

        assertThat(platforms.size(), equalTo(1));
        assertThat(platforms.getFirst().getId().id(), equalTo(savedPlatform.getId()));
    }

    @Test
    void can_find_platform_by_id() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = platformEntityRepository.save(platformEntity);

        Platform foundPlatform = repository.findById(new PlatformId(savedPlatform.getId()));

        assertThat(foundPlatform, notNullValue());
        assertThat(foundPlatform.getId().id(), equalTo(savedPlatform.getId()));
    }

    @Test
    void can_save_platform() {
        Platform platform = PlatformBuilder
                .aPlatform()
                .withId(null)
                .build();

        Platform savedPlatform = repository.save(platform);

        assertThat(savedPlatform, notNullValue());
        assertThat(savedPlatform.getId(), notNullValue());
        assertThat(savedPlatform.getName(), equalTo(platform.getName()));
        assertThat(savedPlatform.getReleaseDate(), equalTo(platform.getReleaseDate()));
        assertThat(savedPlatform.getManufacturer(), equalTo(platform.getManufacturer()));
    }

}