package com.BuilderAI.Project.repository;

import com.BuilderAI.Project.entity.ProjectMember;
import com.BuilderAI.Project.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);
}
