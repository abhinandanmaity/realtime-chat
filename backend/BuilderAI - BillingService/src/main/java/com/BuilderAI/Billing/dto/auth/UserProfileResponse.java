package com.BuilderAI.Billing.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name
) {
}
