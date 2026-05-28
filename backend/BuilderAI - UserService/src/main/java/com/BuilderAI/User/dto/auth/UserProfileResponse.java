package com.BuilderAI.User.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name
) {
}
