package com.basementinteractive.civilwar.resource.model.dto.response;

import com.basementinteractive.civilwar.resource.model.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response object for resource stock")
public class ResourceStockResponse {

    @Schema(description = "The type of the resource", example = "ORE")
    private ResourceType resourceType;

    private Long quantity;

}
