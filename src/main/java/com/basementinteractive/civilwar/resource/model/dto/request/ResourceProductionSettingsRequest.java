package com.basementinteractive.civilwar.resource.model.dto.request;

import com.basementinteractive.civilwar.resource.model.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = "Request object for creating or updating resource production settings")
public class ResourceProductionSettingsRequest {

    @NotNull(message = "must not be null")
    @Size(min = 3, max = 255, message = "must be between 3 and 255 characters")
    @Schema(description = "The name of the resource production setting", example = "Ore x1 Mining Rate")
    private String name;

    @NotNull(message = "must not be null")
    @Schema(description = "The type of the resource", example = "ORE")
    private ResourceType resourceType;

    @NotBlank(message = "must not be blank")
    @Size(max = 255, message = "must be between 3 and 255 characters")
    @Schema(description = "The formula used to calculate the resource production - allowed variable names are LEVEL, WORKERS, and BONUS", example = "60*log(LEVEL*1.5)^BONUS*(WORKERS*1.25)")
    private String formula;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}
