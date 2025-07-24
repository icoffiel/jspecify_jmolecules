package com.icoffiel.jspecify.infra.adapter.controller;

import com.icoffiel.jspecify.application_service.PlatformService;
import com.icoffiel.jspecify.infra.adapter.controller.rest.CreatePlatformDto;
import com.icoffiel.jspecify.infra.adapter.controller.rest.PlatformDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/platform")
public class PlatformController {
    private final PlatformService platformService;

    @GetMapping
    public List<PlatformDto> getPlatforms() {
        return platformService.getPlatforms();
    }

    @GetMapping("/{id}")
    public PlatformDto getPlatform(@PathVariable UUID id) {
        return platformService.getPlatform(id);
    }

    @PostMapping
    public ResponseEntity<PlatformDto> createPlatform(@RequestBody @Validated CreatePlatformDto createPlatformRequestDto) {
        return new ResponseEntity<>(
                platformService.createPlatform(createPlatformRequestDto),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable UUID id) {
        platformService.deletePlatform(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public PlatformDto updatePlatform(@PathVariable UUID id, @RequestBody @Validated PlatformDto platformDto) {
        return platformService.updatePlatform(id, platformDto);
    }
}
