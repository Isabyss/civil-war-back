package com.basementinteractive.civilwar.resource.model.dto.response;

import com.basementinteractive.civilwar.resource.model.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object for resource production settings")
public class ResourceProductionSettingsResponse {

    @Schema(description = "The unique identifier of the resource production setting", example = "1")
    private Long id;

    @Schema(description = "The name of the resource production setting", example = "Ore x1 Mining Rate")
    private String name;

    @Schema(description = "The type of the resource", example = "ORE")
    private ResourceType resourceType;

    @Schema(description = "The formula used to calculate the resource production", example = "60*log(LEVEL*1.5)^BONUS*(WORKERS*1.25)")
    private String formula;

}
