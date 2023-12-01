package com.basementinteractive.civilwar.resource.model.dto.response;

import com.basementinteractive.civilwar.resource.model.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceProductionResponse {

    @Schema(description = "Type of the resource being produced", example = "WOOD")
    private ResourceType resourceType;

    @Schema(description = "Date and time when the resource production was last upgraded",
            type = "string", format = "date-time", example = "2021-04-23T10:20:30Z")
    private LocalDateTime upgradedAt;

    @Schema(description = "Date and time when the resource production was last computed",
            type = "string", format = "date-time", example = "2021-04-24T11:21:31Z")
    private LocalDateTime computedAt;

    @Schema(description = "Current level of resource production", example = "5")
    private Integer level;

    @Schema(description = "Number of workers assigned to this resource production", example = "3")
    private Integer workersAssigned;

    @Schema(description = "Bonus applied to the production of this resource in this specific zone", example = "1.2")
    private Double zoneBonus;

}
