package com.basementinteractive.civilwar.player.controller;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.common.model.dto.response.EntityDeletedResponse;
import com.basementinteractive.civilwar.player.model.dto.request.PlayerRequest;
import com.basementinteractive.civilwar.player.model.dto.response.PlayerResponse;
import com.basementinteractive.civilwar.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
@Tag(name = "Player", description = "Operations pertaining to players in the application")
public class PlayerController {

    private final PlayerService playerService;

    @Operation(summary = "Create a new player", description = "Creates a new player and returns their ID")
    @ApiResponse(responseCode = "201", description = "Player created successfully",
            content = @Content(schema = @Schema(implementation = EntityCreatedResponse.class)))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityCreatedResponse> createPlayer(@Valid @RequestBody PlayerRequest playerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.create(playerRequest));
    }

    @Operation(summary = "Update a player", description = "Updates an existing player")
    @ApiResponse(responseCode = "200", description = "Player updated successfully",
            content = @Content(schema = @Schema(implementation = PlayerResponse.class)))
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponse> updatePlayer(
            @Parameter(description = "ID of the player to be updated", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody PlayerRequest playerRequest) {
        return ResponseEntity.ok(playerService.update(id, playerRequest));
    }

    @Operation(summary = "Delete a player", description = "Deletes a player")
    @ApiResponse(responseCode = "200", description = "Player deleted successfully",
            content = @Content(schema = @Schema(implementation = EntityDeletedResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityDeletedResponse> deletePlayer(
            @Parameter(description = "ID of the player to be deleted", example = "1")
            @PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.ok(new EntityDeletedResponse(id));
    }

    @Operation(summary = "Get player by ID", description = "Retrieves a player based on the given ID")
    @ApiResponse(responseCode = "200", description = "Player retrieved successfully",
            content = @Content(schema = @Schema(implementation = PlayerResponse.class)))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponse> getPlayerById(
            @Parameter(description = "ID of the player to be retrieved", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(playerService.get(id));
    }

    @Operation(summary = "Get all players", description = "Retrieves a list of all players")
    @ApiResponse(responseCode = "200", description = "List of players retrieved successfully",
            content = @Content(schema = @Schema(implementation = PlayerResponse.class)))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAll());
    }

}
