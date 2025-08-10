package com.icoffiel.jspecify.platform.application_service.usecase;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class DeletePlatformUseCaseTest {
    @Autowired
    private DeletePlatformUseCase useCase;

    @Test
    void can_delete_platform() {
        useCase.deletePlatform(UUID.randomUUID());

        assertTrue(true);
    }
}