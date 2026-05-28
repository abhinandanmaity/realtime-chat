package com.BuilderAI.Billing.mapper;


import com.project.BuilderAI.dto.project.ProjectResponse;
import com.project.BuilderAI.dto.project.ProjectSummaryResponse;
import com.project.BuilderAI.entity.Project;
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
