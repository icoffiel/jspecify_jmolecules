package com.icoffiel.jspecify.platform.infra.adapter.platform.persistence.jpa;

import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.model.PlatformId;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaPlatformRepository implements PlatformRepository {

    private final PlatformEntityRepository platformEntityRepository;

    @Override
    public void deleteById(@NonNull PlatformId platformId) {
        platformEntityRepository.deleteById(platformId.id());
    }

    @Override
    public @NonNull List<Platform> findAll() {
        return platformEntityRepository.findAll().stream()
                .map(JpaPlatformRepository::mapToPlatform)
                .toList();
    }

    @Override
    public @NonNull Platform findById(@NonNull PlatformId id) {
        return platformEntityRepository.findById(id.id())
                .map(JpaPlatformRepository::mapToPlatform)
                .orElseThrow(() -> new NotFoundException(id.id()));
    }

    @Override
    public @NonNull Platform save(@NonNull Platform platform) {
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
