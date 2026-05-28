package com.BuilderAI.Billing.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}
