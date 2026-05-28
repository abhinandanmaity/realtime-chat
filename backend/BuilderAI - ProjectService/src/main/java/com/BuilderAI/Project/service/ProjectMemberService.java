package com.BuilderAI.Project.service;

import com.BuilderAI.Project.dto.member.Request.InviteMemberRequest;
import com.BuilderAI.Project.dto.member.Request.UpdateMemberRoleRequest;
import com.BuilderAI.Project.dto.member.Response.MemberResponse;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
