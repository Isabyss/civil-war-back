package com.basementinteractive.civilwar.player.model.dto.response;

import com.basementinteractive.civilwar.player.model.Gender;
import com.basementinteractive.civilwar.playerbase.model.dto.response.PlayerBaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Response object for player")
public class PlayerResponse {

    @Schema(description = "The player's username", example = "gamer90")
    private String username;

    @Schema(description = "The player's email address", example = "player@example.com")
    private String email;

    @Schema(description = "The player's date of birth", example = "1990-01-15")
    private LocalDate dateOfBirth;

    @Schema(description = "The player's gender", example = "MALE")
    private Gender gender;

    @Schema(description = "The player's country of residence", example = "France")
    private String country;

    @Schema(description = "The player's bases")
    private Set<PlayerBaseResponse> bases;

}
