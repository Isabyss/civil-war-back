package com.basementinteractive.civilwar.common.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Response model for indicating successful deletion of an entity")
public class EntityDeletedResponse {

    @Schema(description = "The unique identifier of the deleted entity", example = "1")
    private long id;

}
