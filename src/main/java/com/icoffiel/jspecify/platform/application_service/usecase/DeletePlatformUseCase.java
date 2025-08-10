package com.icoffiel.jspecify.platform.application_service.usecase;

import com.icoffiel.jspecify.UseCase;
import com.icoffiel.jspecify.platform.domain.platform.model.PlatformId;
import com.icoffiel.jspecify.platform.domain.platform.port.PlatformRepository;

import java.util.UUID;

@UseCase
public class DeletePlatformUseCase {
    private final PlatformRepository platformRepository;

    public DeletePlatformUseCase(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public void deletePlatform(UUID id) {
        platformRepository.deleteById(new PlatformId(id));
    }
}
