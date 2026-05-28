package com.BuilderAI.Notification.repository;

import com.project.BuilderAI.entity.ProjectMember;
import com.project.BuilderAI.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);
}
