package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller;

import com.icoffiel.jspecify.platform.application_service.usecase.manufacturer.*;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ManufacturerController.class)
class ManufacturerControllerTest {
    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private CreateManufacturerUseCase createManufacturerUseCase;

    @MockitoBean
    private GetAllManufacturersUseCase getAllManufacturersUseCase;

    @MockitoBean
    private GetManufacturerUseCase getManufacturerUseCase;

    @Test
    @DisplayName("POST /manufacturer returns bad request")
    public void manufacturerReturnsBadRequest() {
        assertThat(
                mockMvcTester
                        .post()
                        .uri("/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .hasStatus(HttpStatus.BAD_REQUEST)
                .bodyJson()
                .isLenientlyEqualTo("""
                        {
                            "type":"about:blank",
                            "title":"Bad Request",
                            "status":400,
                            "detail":"Invalid request content.",
                            "instance":"/manufacturer"
                        }
                        """);
    }

    @Test
    @DisplayName("POST /platform returns created")
    public void manufacturerReturnsCreated() {
        ManufacturerCreatedDto expectedManufacturer = new ManufacturerCreatedDto(
                UUID.randomUUID(),
                "Xbox Series S"
        );

        String requestBody = """
                {
                    "name": "%s"
                }
                """.formatted(
                expectedManufacturer.name()
        );

        given(createManufacturerUseCase.createManufacturer(any())).willReturn(expectedManufacturer);

        assertThat(
                mockMvcTester
                        .post()
                        .uri("/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .hasStatus(HttpStatus.CREATED)
                .bodyJson()
                .isLenientlyEqualTo("""
                        {
                            "id": "%s",
                            "name": "%s"
                        }
                        """.formatted(
                        expectedManufacturer.id().toString(),
                        expectedManufacturer.name()
                ));
    }

    @Test
    @DisplayName("GET /manufacturer returns manufacturers")
    public void getManufacturersReturnsManufacturers() {
        ManufacturerDto manufacturer1 = new ManufacturerDto(
                UUID.randomUUID(),
                "Xbox Series S"
        );
        ManufacturerDto manufacturer2 = new ManufacturerDto(
                UUID.randomUUID(),
                "Xbox Series X"
        );

        given(getAllManufacturersUseCase.getAllManufacturers())
                .willReturn(List.of(manufacturer1, manufacturer2));

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/manufacturer"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        [
                            {
                                "id": "%s",
                                "name": "%s"
                            },
                            {
                                "id": "%s",
                                "name": "%s"
                            }
                        ]
                        """.formatted(
                        manufacturer1.id().toString(),
                        manufacturer1.name(),
                        manufacturer2.id().toString(),
                        manufacturer2.name()
                ));
    }

    @Test
    @DisplayName("GET /manufacturer returns empty list")
    public void getPlatformsReturnsEmptyList() {

        given(getAllManufacturersUseCase.getAllManufacturers()).willReturn(List.of());

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/manufacturer"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        [
                        ]
                        """
                );
    }

    @Test
    @DisplayName("GET /manufacturer/{id} returns manufacturer")
    public void getManufacturerReturnsManufacturer() {
        ManufacturerDto manufacturer = new ManufacturerDto(
                UUID.randomUUID(),
                "Xbox Series S"
        );

        given(getManufacturerUseCase.getManufacturer(any())).willReturn(manufacturer);

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/manufacturer/{id}", manufacturer.id()))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        {
                            "id": "%s",
                            "name": "%s"
                        }
                        """.formatted(
                        manufacturer.id().toString(),
                        manufacturer.name()
                ));
    }

    @Test
    @DisplayName("GET /manufacturer/{id} returns not found")
    public void getManufacturerReturnsNotFound() {
        UUID notFoundId = UUID.randomUUID();

        given(getManufacturerUseCase.getManufacturer(any()))
                .willThrow(new NotFoundException(notFoundId));

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/manufacturer/{id}", notFoundId))
                .hasStatus(HttpStatus.NOT_FOUND)
                .bodyJson()
                .isEqualTo("""
                        {
                            "type":"about:blank",
                            "title":"Not Found",
                            "status": %d,
                            "detail":"Resource not found: %s",
                            "instance":"/manufacturer/%s"
                        }
                        """.formatted(
                        HttpStatus.NOT_FOUND.value(),
                        notFoundId,
                        notFoundId
                ));
    }
}