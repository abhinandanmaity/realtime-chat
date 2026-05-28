package com.BuilderAI.Auth.dto.auth.Response;

public record UserProfileResponse(
        Long id,
        String username,
        String firstName,
        String lastName
) {
}
