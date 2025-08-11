package com.icoffiel.jspecify.platform.infra.adapter.platform.controller;

import com.icoffiel.jspecify.platform.application_service.usecase.platform.*;
import com.icoffiel.jspecify.platform.infra.adapter.platform.controller.dto.CreatePlatformRequest;
import com.icoffiel.jspecify.platform.infra.adapter.platform.controller.dto.PlatformCreatedResponse;
import com.icoffiel.jspecify.platform.infra.adapter.platform.controller.dto.PlatformResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/platform")
public class PlatformController {
    private final CreatePlatformUseCase createPlatformUseCase;
    private final GetAllPlatformsUseCase getAllPlatformsUseCase;
    private final DeletePlatformUseCase deletePlatformUseCase;
    private final GetPlatformUseCase getPlatformUseCase;

    @PostMapping
    public ResponseEntity<PlatformCreatedResponse> createOrder(@Valid @RequestBody CreatePlatformRequest request) {
        CreatePlatformCommand command = new CreatePlatformCommand(
            request.name(),
            request.releaseDate(),
            request.manufacturer()
        );

        PlatformCreatedDto platformCreatedDto = createPlatformUseCase.createPlatform(command);

        PlatformCreatedResponse response = new PlatformCreatedResponse(
            platformCreatedDto.id(),
            platformCreatedDto.name(),
            platformCreatedDto.releaseDate(),
            platformCreatedDto.manufacturer()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PlatformResponse> getPlatforms() {
        List<PlatformDto> platforms = getAllPlatformsUseCase.getAllPlatforms();

        return platforms.stream()
            .map(platform -> new PlatformResponse(
                platform.id(),
                platform.name(),
                platform.releaseDate(),
                platform.manufacturer()
            ))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlatformResponse getPlatform(@PathVariable UUID id) {
        PlatformDto platform = getPlatformUseCase.getPlatform(id);
        return new PlatformResponse(
            platform.id(),
            platform.name(),
            platform.releaseDate(),
            platform.manufacturer()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable UUID id) {
        deletePlatformUseCase.deletePlatform(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
