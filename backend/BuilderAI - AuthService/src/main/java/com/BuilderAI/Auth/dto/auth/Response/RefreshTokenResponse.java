package com.BuilderAI.Auth.dto.auth.Response;

public record RefreshTokenResponse(
        String accessToken,
        String token
) {
}
