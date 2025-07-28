package com.icoffiel.jspecify.infra.adapter.controller;

import com.icoffiel.jspecify.application_service.usecase.CreatePlatformUseCase;
import com.icoffiel.jspecify.application_service.usecase.GetAllPlatformsUseCase;
import com.icoffiel.jspecify.application_service.usecase.PlatformCreatedDto;
import com.icoffiel.jspecify.application_service.usecase.PlatformDto;
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
                "Microsoft"
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
                expectedPlatform.manufacturer()
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
                .isLenientlyEqualTo("""
                        {
                            "id": "%s",
                            "name": "%s",
                            "releaseDate": "%s",
                            "manufacturer": "%s"
                        }
                        """.formatted(
                        expectedPlatform.id().toString(),
                        expectedPlatform.name(),
                        expectedPlatform.releaseDate(),
                        expectedPlatform.manufacturer()
                ));
    }

    @Test
    @DisplayName("GET /platform returns platforms")
    public void getPlatformsReturnsPlatforms() {
        PlatformDto platform1 = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series S",
                LocalDate.parse("2020-11-12"),
                "Microsoft"
        );
        PlatformDto platform2 = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series X",
                LocalDate.parse("2021-11-12"),
                "Microsoft"
        );

        given(getAllPlatformsUseCase.getAllPlatforms()).willReturn(List.of(platform1, platform2));

        assertThat(
                mockMvcTester
                        .get()
                        .uri("/platform"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo("""
                        [
                            {
                                "id": "%s",
                                "name": "%s",
                                "releaseDate": "%s",
                                "manufacturer": "%s"
                            },
                            {
                                "id": "%s",
                                "name": "%s",
                                "releaseDate": "%s",
                                "manufacturer": "%s"
                            }
                        ]
                        """.formatted(
                        platform1.id().toString(),
                        platform1.name(),
                        platform1.releaseDate(),
                        platform1.manufacturer(),
                        platform2.id().toString(),
                        platform2.name(),
                        platform2.releaseDate(),
                        platform2.manufacturer()
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
                .isLenientlyEqualTo("""
                        [
                        ]
                        """
                );
    }

}
