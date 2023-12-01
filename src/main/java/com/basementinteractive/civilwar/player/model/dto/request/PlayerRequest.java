package com.basementinteractive.civilwar.player.model.dto.request;

import com.basementinteractive.civilwar.player.model.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "Request object for creating or updating player")
public class PlayerRequest {

    @NotBlank(message = "must not be blank")
    @Size(min = 3, max = 50, message = "must be between 3 and 50 characters")
    @Schema(description = "The player's username", example = "gamer90")
    private String username;

    @NotBlank(message = "must not be blank")
    @Size(min = 3, max = 64, message = "must be between 3 and 64 characters")
    @Schema(description = "The player's password in encrypted form", example = "encryptedPassword")
    private String password;

    @NotBlank(message = "must not be blank")
    @Size(min = 3, max = 100, message = "must be between 3 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "format is invalid")
    @Schema(description = "The player's email address", example = "player@example.com")
    private String email;

    @NotNull(message = "must not be null")
    @Past(message = "must be a date in the past")
    @Schema(description = "The player's date of birth", example = "1990-01-15")
    private LocalDate dateOfBirth;

    @NotNull(message = "must not be null")
    @Schema(description = "The player's gender", example = "MALE")
    private Gender gender;

    @NotBlank(message = "must not be blank")
    @Size(min = 3, max = 30, message = "must be between 3 and 30 characters")
    @Schema(description = "The player's country of residence", example = "France")
    private String country;

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim().toLowerCase();
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

}
