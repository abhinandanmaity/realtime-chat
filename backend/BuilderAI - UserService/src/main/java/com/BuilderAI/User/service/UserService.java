package com.BuilderAI.User.service;


import com.BuilderAI.User.dto.auth.UserProfileResponse;
import com.BuilderAI.User.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserProfileResponse getProfile(Long userId);

    UserProfileResponse getUserByUsername(String username);

    UserProfileResponse getUserById(Long id);

    Map<Long, UserProfileResponse> getUsersByIds(List<Long> id);
}
