package com.icoffiel.jspecify.platform.domain.port;

import com.icoffiel.jspecify.platform.domain.model.Platform;
import com.icoffiel.jspecify.platform.domain.model.PlatformId;

import java.util.List;

public interface PlatformRepository {
    void deleteById(PlatformId platformId);
    List<Platform> findAll();
    Platform findById(PlatformId id);
    Platform save(Platform platform);
}
