package com.icoffiel.jspecify.platform;

import com.icoffiel.jspecify.exception.NotFoundException;
import com.icoffiel.jspecify.platform.rest.PlatformDto;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PlatformController.class)
public class PlatformControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private PlatformService platformService;

    @Test
    @DisplayName("GET /platform returns empty list of platforms")
    public void platformReturnsEmptyListOfPlatforms() {
        given(this.platformService.getPlatforms())
                .willReturn(List.of());

        assertThat(mockMvcTester.get().uri("/platform"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo("""
                            []
                        """
                );
    }

    @Test
    @DisplayName("GET /platform returns list of platforms")
    public void platformReturnsListOfPlatforms() {
        UUID id = UUID.randomUUID();
        given(this.platformService.getPlatforms())
                .willReturn(
                        List.of(
                                new PlatformDto(
                                        id,
                                        "Xbox Series S",
                                        "2020-11-12",
                                        "Microsoft"
                                )
                        )
                );

        assertThat(mockMvcTester.get().uri("/platform"))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo("""
                            [
                                {
                                    "id": "%s",
                                    "name": "Xbox Series S",
                                    "releaseDate": "2020-11-12",
                                    "manufacturer": "Microsoft"
                                }
                            ]
                        """.formatted(id.toString())
                );
    }

    @Test
    @DisplayName("GET /platform/{id} returns not found")
    public void unknownPlatformReturnsNotFound() {
        UUID id = UUID.randomUUID();
        given(platformService.getPlatform(id))
                .willThrow(new NotFoundException(id));


        assertThat(mockMvcTester.get().uri("/platform/" + id))
                .hasStatus(HttpStatus.NOT_FOUND)
                .bodyJson()
                .isLenientlyEqualTo("""
                                {
                                    "type": "about:blank",
                                    "title": "Not Found",
                                    "status": 404,
                                    "detail": "Resource not found: %s"
                                }
                        """.formatted(id.toString())
                );
    }

    @Test
    @DisplayName("GET /platform/{id} returns a found platform")
    public void platformReturnsAFoundPlatform() {
        UUID id = UUID.randomUUID();
        given(this.platformService.getPlatform(id))
                .willReturn(
                        new PlatformDto(
                                id,
                                "Xbox Series S",
                                "2020-11-12",
                                "Microsoft"
                        )

                );

        assertThat(mockMvcTester.get().uri("/platform/" + id))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo("""
                            {
                                    "id": "%s",
                                    "name": "Xbox Series S",
                                    "releaseDate": "2020-11-12",
                                    "manufacturer": "Microsoft"
                                }
                        """.formatted(id.toString())
                );
    }

    @Test
    @DisplayName("POST /platform returns bad request")
    public void platformReturnsBadRequest() {
        String requestBody = """
                {
                }
                """;

        assertThat(
                mockMvcTester
                        .post()
                        .uri("/platform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
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
        PlatformDto expectedPlatform = new PlatformDto(
                UUID.randomUUID(),
                "Xbox Series S",
                "2020-11-12",
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

        given(platformService.createPlatform(any())).willReturn(expectedPlatform);

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
    @DisplayName("DELETE /platform/{id} returns no content")
    public void platformReturnsNoContent() {
        UUID id = UUID.randomUUID();

        assertThat(
                mockMvcTester
                        .delete()
                        .uri("/platform/" + id))
                .hasStatus(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("PUT /platform/{id} returns bad request")
    public void updatePlatformReturnsBadRequest() {
        String requestBody = """
                {
                }
                """;

        UUID id = UUID.randomUUID();

        assertThat(
                mockMvcTester
                        .put()
                        .uri("/platform/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .hasStatus(HttpStatus.BAD_REQUEST)
                .bodyJson()
                .isLenientlyEqualTo("""
                        {
                            "type":"about:blank",
                            "title":"Bad Request",
                            "status":400,
                            "detail":"Invalid request content.",
                            "instance":"/platform/%s"
                        }
                        """.formatted(id.toString()));
    }

    @Test
    @DisplayName("PUT /platform/{id} returns not found")
    public void updatePlatformReturnsNotFound() {
        UUID id = UUID.randomUUID();

        String requestBody = """
                {
                    "id": "%s",
                    "name": "Xbox Series S",
                    "releaseDate": "2020-11-12",
                    "manufacturer": "Microsoft"
                }
                """.formatted(id.toString());

        given(platformService.updatePlatform(eq(id), any(PlatformDto.class)))
                .willThrow(new NotFoundException(id));

        assertThat(
                mockMvcTester
                        .put()
                        .uri("/platform/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .hasStatus(HttpStatus.NOT_FOUND)
                .bodyJson()
                .isLenientlyEqualTo("""
                                {
                                    "type": "about:blank",
                                    "title": "Not Found",
                                    "status": 404,
                                    "detail": "Resource not found: %s"
                                }
                        """.formatted(id.toString())
                );
    }

    @Test
    @DisplayName("PUT /platform/{id} returns updated platform")
    public void updatePlatformReturnsUpdatedPlatform() {
        UUID id = UUID.randomUUID();
        PlatformDto expectedPlatform = new PlatformDto(
                id,
                "Xbox Series S",
                "2020-11-12",
                "Microsoft"
        );

        String requestBody = """
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
        );

        given(platformService.updatePlatform(eq(id), any(PlatformDto.class))).willReturn(expectedPlatform);

        assertThat(
                mockMvcTester
                        .put()
                        .uri("/platform/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo(requestBody);
    }
}
