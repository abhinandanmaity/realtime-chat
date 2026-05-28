package com.BuilderAI.Notification.service;


import com.project.BuilderAI.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
