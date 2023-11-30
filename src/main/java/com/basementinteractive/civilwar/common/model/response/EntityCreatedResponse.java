package com.basementinteractive.civilwar.common.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Response model for indicating successful creation of an entity")
public class EntityCreatedResponse {

    @Schema(description = "The unique identifier of the newly created entity", example = "1")
    private long id;

}
