package com.BuilderAI.Auth.service;

import com.BuilderAI.Auth.dto.auth.Response.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
