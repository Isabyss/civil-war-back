package com.basementinteractive.civilwar.playerbase.controller;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.playerbase.model.dto.request.PlayerBaseRequest;
import com.basementinteractive.civilwar.playerbase.service.PlayerBaseCommandService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bases")
@Tag(name = "Player bases", description = "Operations pertaining to player base management in the game")
public class PlayerBaseController {

    private final PlayerBaseCommandService playerBaseCommandService;

    @Operation(summary = "Create a new player base", description = "Creates a new player base and returns its ID")
    @ApiResponse(responseCode = "201", description = "Player base created successfully",
            content = @Content(schema = @Schema(implementation = EntityCreatedResponse.class)))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityCreatedResponse> createPlayerBase(@Valid @RequestBody PlayerBaseRequest playerBaseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerBaseCommandService.create(playerBaseRequest));
    }

}
