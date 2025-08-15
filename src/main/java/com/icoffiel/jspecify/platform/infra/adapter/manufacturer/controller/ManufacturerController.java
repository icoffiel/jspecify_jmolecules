package com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller;

import com.icoffiel.jspecify.platform.application_service.usecase.manufacturer.*;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller.dto.CreateManfacturerRequest;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller.dto.ManufacturerCreatedResponse;
import com.icoffiel.jspecify.platform.infra.adapter.manufacturer.controller.dto.ManufacturerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final CreateManufacturerUseCase createManufacturerUseCase;
    private final GetAllManufacturersUseCase getAllManufacturersUseCase;
    private final GetManufacturerUseCase getManufacturerUseCase;

    @PostMapping
    public ResponseEntity<ManufacturerCreatedResponse> createManufacturer(@Valid @RequestBody CreateManfacturerRequest request) {
        CreateManufacturerCommand command = new CreateManufacturerCommand(request.name());

        ManufacturerCreatedDto manufacturerCreatedDto = createManufacturerUseCase.createManufacturer(command);

        ManufacturerCreatedResponse response = new ManufacturerCreatedResponse(
            manufacturerCreatedDto.id(),
            manufacturerCreatedDto.name()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ManufacturerResponse> getManufacturers() {
        return getAllManufacturersUseCase.getAllManufacturers().stream()
            .map(manufacturer ->
                    new ManufacturerResponse(
                            manufacturer.id(),
                            manufacturer.name()
                    )
            )
            .toList();
    }

    @GetMapping("/{id}")
    public ManufacturerResponse getManufacturer(@PathVariable UUID id) {
        ManufacturerDto manufacturer = getManufacturerUseCase.getManufacturer(id);
        return new ManufacturerResponse(
            manufacturer.id(),
            manufacturer.name()
        );
    }
}
