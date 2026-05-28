package com.BuilderAI.Notification.service.impl;


import com.project.BuilderAI.dto.project.ProjectRequest;
import com.project.BuilderAI.dto.project.ProjectResponse;
import com.project.BuilderAI.dto.project.ProjectSummaryResponse;
import com.project.BuilderAI.entity.Project;
import com.project.BuilderAI.entity.ProjectMember;
import com.project.BuilderAI.entity.ProjectMemberId;
import com.project.BuilderAI.entity.User;
import com.project.BuilderAI.enums.ProjectRole;
import com.project.BuilderAI.error.ResourceNotFoundException;
import com.project.BuilderAI.mapper.ProjectMapper;
import com.project.BuilderAI.repository.ProjectMemberRepository;
import com.project.BuilderAI.repository.ProjectRepository;
import com.project.BuilderAI.repository.UserRepository;
import com.project.BuilderAI.security.AuthUtil;
import com.project.BuilderAI.service.ProjectService;
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
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
//        User owner = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User", userId.toString())
//        );
        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        project = projectRepository.save(project);


        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = authUtil.getCurrentUserId();
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
