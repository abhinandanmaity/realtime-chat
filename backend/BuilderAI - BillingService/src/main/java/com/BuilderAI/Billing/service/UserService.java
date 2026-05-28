package com.BuilderAI.Billing.service;


import com.project.BuilderAI.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
