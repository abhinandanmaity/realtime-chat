package com.BuilderAI.Auth.dto.auth.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @Email @NotBlank String username,
        @Size(min = 1, max = 30) String firstName,
        @Size(min = 1, max = 20) String lastName,
        @Size(min = 4) String password
) {
}
