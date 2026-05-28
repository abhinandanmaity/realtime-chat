package com.BuilderAI.Project.service.impl;

import com.BuilderAI.Project.client.UserClient;
import com.BuilderAI.Project.dto.member.Request.InviteMemberRequest;
import com.BuilderAI.Project.dto.member.Request.UpdateMemberRoleRequest;
import com.BuilderAI.Project.dto.member.Response.MemberResponse;
import com.BuilderAI.Project.dto.user.Response.UserProfileResponse;
import com.BuilderAI.Project.entity.Project;
import com.BuilderAI.Project.entity.ProjectMember;
import com.BuilderAI.Project.entity.ProjectMemberId;
import com.BuilderAI.Project.mapper.ProjectMemberMapper;
import com.BuilderAI.Project.repository.ProjectMemberRepository;
import com.BuilderAI.Project.repository.ProjectRepository;
import com.BuilderAI.Project.security.TokenUtil;
import com.BuilderAI.Project.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    TokenUtil tokenUtil;
    UserClient userClient;


    @Override
    public List<MemberResponse> getProjectMembers(Long projectId) {
//        Long userId = tokenUtil.getCurrentUserId();
//        Project project = getAccessibleProjectById(projectId, userId);
//
//        return projectMemberRepository.findByIdProjectId(projectId)
//                .stream()
//                .map(projectMemberMapper::toProjectMemberResponseFromMember)
//                .toList();

//        Long userId = tokenUtil.getCurrentUserId();
//        Project project = getAccessibleProjectById(projectId, userId);

        List<ProjectMember> members =
                projectMemberRepository.findByIdProjectId(projectId);

        // ✅ Step 1: collect userIds
        List<Long> userIds = members.stream()
                .map(ProjectMember::getUserId)
                .toList();

        // ✅ Step 2: fetch users in bulk
        Map<Long, UserProfileResponse> userMap =
                userClient.getUsersByIds(userIds);

        // ✅ Step 3: map with both inputs
        return members.stream()
                .map(member -> projectMemberMapper.toProjectMemberResponseFromMember(
                        member,
                        userMap.get(member.getUserId())
                ))
                .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        // ✅ Call User Service
        UserProfileResponse invitee = userClient.getUserByUsername(request.username());
//        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitee.id().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.id());

        if(projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
//                .userId(invitee.id())
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member, invitee);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        // ✅ Fetch user from User Service
        UserProfileResponse user =
                userClient.getUserById(projectMember.getUserId());

        // ✅ Use mapper with both inputs
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember, user);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    ///  INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
