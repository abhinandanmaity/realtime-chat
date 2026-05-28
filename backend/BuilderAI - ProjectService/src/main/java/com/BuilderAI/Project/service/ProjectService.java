package com.BuilderAI.Project.service;


import com.BuilderAI.Project.dto.project.Request.ProjectRequest;
import com.BuilderAI.Project.dto.project.Response.ProjectResponse;
import com.BuilderAI.Project.dto.project.Response.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
