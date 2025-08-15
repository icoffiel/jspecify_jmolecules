package com.icoffiel.jspecify.platform.application_service.usecase.manufacturer;

import com.icoffiel.jspecify.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class CreateManufacturerUseCaseTest {
    @Autowired
    private CreateManufacturerUseCase createManufacturerUseCase;

    @Test
    void can_create_manufacturer() {
        // given
        CreateManufacturerCommand command = new CreateManufacturerCommand("test");

        // when
        ManufacturerCreatedDto result = createManufacturerUseCase.createManufacturer(command);

        // then
        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull();
        assertThat(command.name()).isEqualTo(result.name());
    }
}