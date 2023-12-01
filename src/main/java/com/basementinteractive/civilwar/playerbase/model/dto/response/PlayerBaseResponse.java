package com.basementinteractive.civilwar.playerbase.model.dto.response;

import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionResponse;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceStockResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Schema(description = "Response object for player base")
public class PlayerBaseResponse {

    @Schema(description = "The X coordinate of the player base", example = "10")
    private Integer x;

    @Schema(description = "The Y coordinate of the player base", example = "20")
    private Integer y;

    @Schema(description = "The level of the player base", example = "3")
    private Integer level;

    @Schema(description = "Total number of workers in the player base", example = "10")
    private Integer totalWorkers;

    @Schema(description = "Player's bonus for this base", example = "1.5")
    private Double playerBonus;

    @Schema(description = "Resource productions information associated with the player base")
    private Set<ResourceProductionResponse> resourceProductions;

    @Schema(description = "Resource stocks associated with the player base")
    private Set<ResourceStockResponse> resourceStocks;

}
