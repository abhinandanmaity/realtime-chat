package com.BuilderAI.File.mapper;

import com.project.BuilderAI.dto.auth.SignupRequest;
import com.project.BuilderAI.dto.auth.UserProfileResponse;
import com.project.BuilderAI.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

}
