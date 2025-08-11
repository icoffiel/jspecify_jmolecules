package com.icoffiel.jspecify.platform.infra.adapter.persistence.jpa;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import com.icoffiel.jspecify.infra.adapter.persistence.jpa.PlatformEntityBuilder;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntity;
import com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa.PlatformEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
class PlatformEntityRepositoryTest {
    @Autowired
    private PlatformEntityRepository repository;

    @Test
    void can_delete_platform() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = repository.save(platformEntity);

        repository.deleteById(savedPlatform.getId());

        PlatformEntity foundPlatform = repository.findById(savedPlatform.getId()).orElse(null);

        assertThat(foundPlatform, is(nullValue()));
    }

    @Test
    void can_find_all_platforms() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = repository.save(platformEntity);

        List<PlatformEntity> platforms = repository.findAll();

        assertThat(platforms, hasSize(1));
        assertThat(platforms, hasItem(savedPlatform));
    }

    @Test
    void can_find_platform() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = repository.save(platformEntity);

        PlatformEntity foundPlatform = repository.findById(savedPlatform.getId()).orElse(null);

        assertThat(foundPlatform, notNullValue());
        assertThat(foundPlatform.getName(), is(platformEntity.getName()));
        assertThat(foundPlatform.getReleaseDate(), is(platformEntity.getReleaseDate()));
        assertThat(foundPlatform.getManufacturer(), is(platformEntity.getManufacturer()));
    }

    @Test
    void can_save_platform() {
        PlatformEntity platformEntity = PlatformEntityBuilder
                .aPlatformEntity()
                .withId(null)
                .build();

        PlatformEntity savedPlatform = repository.save(platformEntity);

        assertThat(savedPlatform, notNullValue());
        assertThat(savedPlatform.getName(), is(platformEntity.getName()));
        assertThat(savedPlatform.getReleaseDate(), is(platformEntity.getReleaseDate()));
        assertThat(savedPlatform.getManufacturer(), is(platformEntity.getManufacturer()));
    }
}