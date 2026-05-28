package com.BuilderAI.Auth.mapper;

import com.BuilderAI.Auth.dto.auth.Request.SignupRequest;
import com.BuilderAI.Auth.dto.auth.Response.UserProfileResponse;
import com.BuilderAI.Auth.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

}
