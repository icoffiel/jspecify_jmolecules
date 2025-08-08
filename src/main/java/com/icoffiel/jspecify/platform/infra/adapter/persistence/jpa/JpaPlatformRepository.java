package com.icoffiel.jspecify.platform.infra.adapter.persistence.jpa;

import com.icoffiel.jspecify.platform.domain.model.Platform;
import com.icoffiel.jspecify.platform.domain.model.PlatformId;
import com.icoffiel.jspecify.platform.domain.port.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaPlatformRepository implements PlatformRepository {

    private final PlatformEntityRepository platformEntityRepository;

    @Override
    public void deleteById(PlatformId platformId) {
        platformEntityRepository.deleteById(platformId.id());
    }

    @Override
    public List<Platform> findAll() {
        return platformEntityRepository.findAll().stream()
                .map(JpaPlatformRepository::mapToPlatform)
                .toList();
    }

    @Override
    public Platform findById(PlatformId id) {
        return platformEntityRepository.findById(id.id())
                .map(JpaPlatformRepository::mapToPlatform)
                .orElse(null);
    }

    @Override
    public Platform save(Platform platform) {
        PlatformEntity entity = new PlatformEntity(
                platform.getName(),
                platform.getReleaseDate(),
                platform.getManufacturer()
        );

        PlatformEntity savedEntity = platformEntityRepository.save(entity);

        return mapToPlatform(savedEntity);
    }

    private static Platform mapToPlatform(PlatformEntity entity) {
        return new Platform(
                new PlatformId(entity.getId()),
                entity.getName(),
                entity.getReleaseDate(),
                entity.getManufacturer());
    }
}
