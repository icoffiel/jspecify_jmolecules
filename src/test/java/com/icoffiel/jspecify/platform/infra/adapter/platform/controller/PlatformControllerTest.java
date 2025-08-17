package com.icoffiel.jspecify.platform.infra.adapter.platform.controller;

import com.icoffiel.jspecify.platform.application_service.usecase.platform.*;
import com.icoffiel.jspecify.platform.infra.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PlatformController.class)
public class PlatformControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private CreatePlatformUseCase createPlatformUseCase;

    @MockitoBean
    private GetAllPlatformsUseCase getAllPlatformsUseCase;

    @MockitoBean
    private GetPlatformUseCase getPlatformUseCase;

    @MockitoBean
    private DeletePlatformUseCase deletePlatformUseCase;

    @Test
    @DisplayName("POST /platform returns bad request")
    public void platformReturnsBadRequest() {
        assertThat(
                mockMvcTester
                        .post()
                        .uri("/platform")
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
                            "instance":"/platform"
                        }
                        """);
    }

    @Test
    @DisplayName("POST /platform returns created")
    public void platformReturnsCreated() {
        PlatformCreatedDto expectedPlatform = new PlatformCreatedDto(
                UUID.randomUUID(),
                "Xbox Series S",
                LocalDate.parse("2020-11-12"),
                UUID.randomUUID()
        );

        String requestBody = """
                {
                    "name": "%s",
                    "releaseDate": "%s",
                    "manufacturer": "%s"
                }
                """.formatted(
                expectedPlatform.name(),
                expectedPlatform.releaseDate(),
                expectedPlatform.manufacturerId()
        );

        given(createPlatformUseCase.createPlatform(any())).willReturn(expectedPlatform);

        assertThat(
                mockMvcTester
                        .post()
                        .uri("/platform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .hasStatus(HttpStatus.CREATED)
                .bodyJson()
                .isEqualTo("""
                        {
                            "id": "%s",
                            "name": "%s",
                            "releaseDate": "%s",
                            "manufacturerId": "%s"
                        }
                        """.formatted(
                        expectedPlatform.id().toString(),
                        expectedPlatform.name(),
                        expectedPlatform.releaseDate(),
                        expectedPlatform.manufacturerId()
                ));
    }

    @Test
    @DisplayName("GET /platform returns platforms")
    public void getPlatformsReturnsPlatforms() {
        PlatformDto platform1 = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series S",
                LocalDate.parse("2020-11-12"),
                UUID.randomUUID()
        );
        PlatformDto platform2 = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series X",
                LocalDate.parse("2021-11-12"),
                UUID.randomUUID()
        );

        given(getAllPlatformsUseCase.getAllPlatforms()).willReturn(List.of(platform1, platform2));

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/platform"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        [
                            {
                                "id": "%s",
                                "name": "%s",
                                "releaseDate": "%s",
                                "manufacturerId": "%s"
                            },
                            {
                                "id": "%s",
                                "name": "%s",
                                "releaseDate": "%s",
                                "manufacturerId": "%s"
                            }
                        ]
                        """.formatted(
                        platform1.id().toString(),
                        platform1.name(),
                        platform1.releaseDate(),
                        platform1.manufacturerId(),
                        platform2.id().toString(),
                        platform2.name(),
                        platform2.releaseDate(),
                        platform2.manufacturerId()
                ));
    }

    @Test
    @DisplayName("GET /platform returns empty list")
    public void getPlatformsReturnsEmptyList() {

        given(getAllPlatformsUseCase.getAllPlatforms()).willReturn(List.of());

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/platform"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        [
                        ]
                        """
                );
    }

    @Test
    @DisplayName("GET /platform/{id} returns platform")
    public void getPlatformReturnsPlatform() {
        PlatformDto platform = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series S",
                LocalDate.parse("2020-11-12"),
                UUID.randomUUID()
        );

        given(getPlatformUseCase.getPlatform(any())).willReturn(platform);

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/platform/{id}", platform.id()))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        {
                            "id": "%s",
                            "name": "%s",
                            "releaseDate": "%s",
                            "manufacturerId": "%s"
                        }
                        """.formatted(
                        platform.id().toString(),
                        platform.name(),
                        platform.releaseDate(),
                        platform.manufacturerId()
                ));
    }

    @Test
    @DisplayName("GET /platform/{id} returns not found")
    public void getPlatformReturnsNotFound() {
        UUID notFoundId = UUID.randomUUID();

        given(getPlatformUseCase.getPlatform(any())).willThrow(new NotFoundException(notFoundId));

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/platform/{id}", notFoundId))
                .hasStatus(HttpStatus.NOT_FOUND)
                .bodyJson()
                .isEqualTo("""
                            {
                              "type":"about:blank",
                              "title":"Not Found",
                              "status": %d,
                              "detail":"Resource not found: %s",
                              "instance":"/platform/%s"
                          }
                        """.formatted(
                        HttpStatus.NOT_FOUND.value(),
                        notFoundId,
                        notFoundId
                ));
    }

    @Test
    @DisplayName("DELETE /platform returns no content")
    public void deletePlatformReturnsNoContent() {
        assertThat(
                mockMvcTester
                        .delete()
                        .uri("/platform/{id}", UUID.randomUUID()))
                .hasStatus(HttpStatus.NO_CONTENT);
    }

}
