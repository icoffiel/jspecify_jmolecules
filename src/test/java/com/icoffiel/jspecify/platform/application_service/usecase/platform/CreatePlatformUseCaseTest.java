package com.icoffiel.jspecify.platform.application_service.usecase.platform;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class CreatePlatformUseCaseTest {
    @Autowired
    private CreatePlatformUseCase useCase;

    @Test
    void can_create_platform() {
        // given
        CreatePlatformCommand command = new CreatePlatformCommand(
                "platform",
                LocalDate.now(),
                "manufacturer"
        );

        // when
        PlatformCreatedDto result = useCase.createPlatform(command);

        // then
        assertNotNull(result);
        assertEquals(command.name(), result.name());
        assertEquals(command.releaseDate(), result.releaseDate());
        assertEquals(command.manufacturer(), result.manufacturer());
    }
}