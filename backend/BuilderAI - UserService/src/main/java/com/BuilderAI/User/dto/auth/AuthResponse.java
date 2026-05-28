package com.BuilderAI.User.dto.auth;


public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}
