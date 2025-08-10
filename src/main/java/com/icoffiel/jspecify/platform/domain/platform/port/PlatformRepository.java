package com.icoffiel.jspecify.platform.domain.platform.port;

import com.icoffiel.jspecify.platform.domain.platform.model.Platform;
import com.icoffiel.jspecify.platform.domain.platform.model.PlatformId;

import java.util.List;

public interface PlatformRepository {
    void deleteById(PlatformId platformId);
    List<Platform> findAll();
    Platform findById(PlatformId id);
    Platform save(Platform platform);
}
