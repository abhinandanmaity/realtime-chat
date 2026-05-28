package com.BuilderAI.Project.service.impl;


import com.BuilderAI.Project.client.UserClient;
import com.BuilderAI.Project.dto.project.Request.ProjectRequest;
import com.BuilderAI.Project.dto.project.Response.ProjectResponse;
import com.BuilderAI.Project.dto.project.Response.ProjectSummaryResponse;
import com.BuilderAI.Project.dto.user.Response.UserProfileResponse;
import com.BuilderAI.Project.entity.Project;
import com.BuilderAI.Project.entity.ProjectMember;
import com.BuilderAI.Project.entity.ProjectMemberId;
import com.BuilderAI.Project.enums.ProjectRole;
import com.BuilderAI.Project.error.ResourceNotFoundException;
import com.BuilderAI.Project.mapper.ProjectMapper;
import com.BuilderAI.Project.repository.ProjectMemberRepository;
import com.BuilderAI.Project.repository.ProjectRepository;
import com.BuilderAI.Project.security.TokenUtil;
import com.BuilderAI.Project.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    TokenUtil tokenUtil;
    UserClient userClient;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = tokenUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User", userId.toString())
//        );
        UserProfileResponse owner = userClient.getUserById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        project = projectRepository.save(project);


        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.id());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
//                .userId(owner.id())
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = tokenUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = tokenUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    ///  INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));
    }
}
