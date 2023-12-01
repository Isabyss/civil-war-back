package com.basementinteractive.civilwar.resource.controller;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.common.model.dto.response.EntityDeletedResponse;
import com.basementinteractive.civilwar.resource.model.dto.request.ResourceProductionSettingsRequest;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionSettingsResponse;
import com.basementinteractive.civilwar.resource.service.ResourceProductionSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/resource-production-settings")
@Tag(name = "Resource production settings", description = "Operations pertaining to resource production settings in the application")
public class ResourceProductionSettingsController {

    private final ResourceProductionSettingsService resourceProductionSettingsService;

    @Operation(summary = "Create a new resource production setting", description = "Creates a new resource production setting and returns its ID")
    @ApiResponse(responseCode = "201", description = "Resource production setting created successfully",
            content = @Content(schema = @Schema(implementation = EntityCreatedResponse.class)))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityCreatedResponse> createResourceProductionSettings(@Valid @RequestBody ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resourceProductionSettingsService.create(resourceProductionSettingsRequest));
    }

    @Operation(summary = "Update a resource production setting", description = "Updates an existing resource production setting")
    @ApiResponse(responseCode = "200", description = "Resource production setting updated successfully",
            content = @Content(schema = @Schema(implementation = ResourceProductionSettingsResponse.class)))
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceProductionSettingsResponse> updateResourceProductionSettings(
            @Parameter(description = "ID of the resource production setting to be updated", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        return ResponseEntity.ok(resourceProductionSettingsService.update(id, resourceProductionSettingsRequest));
    }

    @Operation(summary = "Delete a resource production setting", description = "Deletes a resource production setting")
    @ApiResponse(responseCode = "200", description = "Resource production setting deleted successfully",
            content = @Content(schema = @Schema(implementation = EntityDeletedResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityDeletedResponse> deleteResourceProductionSettings(
            @Parameter(description = "ID of the resource production setting to be deleted", example = "1")
            @PathVariable Long id) {
        resourceProductionSettingsService.delete(id);
        return ResponseEntity.ok(new EntityDeletedResponse(id));
    }

    @Operation(summary = "Get resource production setting by ID", description = "Retrieves a resource production setting based on the given ID")
    @ApiResponse(responseCode = "200", description = "Resource production setting retrieved successfully",
            content = @Content(schema = @Schema(implementation = ResourceProductionSettingsResponse.class)))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceProductionSettingsResponse> getById(
            @Parameter(description = "ID of the resource production setting to be retrieved", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(resourceProductionSettingsService.get(id));
    }

    @Operation(summary = "Get all resource production settings", description = "Retrieves a list of all resource production settings")
    @ApiResponse(responseCode = "200", description = "List of resource production settings retrieved successfully",
            content = @Content(schema = @Schema(implementation = ResourceProductionSettingsResponse.class)))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceProductionSettingsResponse>> getAll() {
        return ResponseEntity.ok(resourceProductionSettingsService.getAll());
    }

}
