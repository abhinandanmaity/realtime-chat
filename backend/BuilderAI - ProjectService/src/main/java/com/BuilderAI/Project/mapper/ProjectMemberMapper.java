package com.BuilderAI.Project.mapper;


import com.BuilderAI.Project.dto.member.Response.MemberResponse;
import com.BuilderAI.Project.dto.user.Response.UserProfileResponse;
import com.BuilderAI.Project.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(UserProfileResponse owner);

//    @Mapping(target = "userId", source = "id")
//    @Mapping(target = "username", source = "username")
//    @Mapping(target = "name", source = "name")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "projectRole", source = "projectMember.projectRole")
    @Mapping(target = "invitedAt", source = "projectMember.invitedAt")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember, UserProfileResponse user);
}
