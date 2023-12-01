package com.basementinteractive.civilwar.playerbase.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "Request object for creating or updating player base")
public class PlayerBaseRequest {

    @NotNull(message = "must not be null")
    @Min(value = 0, message = "must be greater than or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    @Schema(description = "The X coordinate of the player base on the map", example = "25")
    private Integer x;

    @NotNull(message = "must not be null")
    @Min(value = 0, message = "must be greater than or equal to 0")
    @Max(value = 100, message = "must be less than or equal to 100")
    @Schema(description = "The Y coordinate of the player base on the map", example = "75")
    private Integer y;

}
