package com.BuilderAI.User.mapper;

import com.BuilderAI.User.dto.auth.SignupRequest;
import com.BuilderAI.User.dto.auth.UserProfileResponse;
import com.BuilderAI.User.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

}
