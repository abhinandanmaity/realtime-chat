package com.BuilderAI.Project.mapper;


import com.BuilderAI.Project.dto.project.Response.ProjectResponse;
import com.BuilderAI.Project.dto.project.Response.ProjectSummaryResponse;
import com.BuilderAI.Project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    @Mapping(target = "projectName", source = "name")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
