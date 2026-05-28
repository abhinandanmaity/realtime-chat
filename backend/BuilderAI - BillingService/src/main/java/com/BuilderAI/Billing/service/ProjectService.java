package com.BuilderAI.Billing.service;

import com.project.BuilderAI.dto.project.ProjectRequest;
import com.project.BuilderAI.dto.project.ProjectResponse;
import com.project.BuilderAI.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
