package com.BuilderAI.Project.dto.member.Response;

import com.BuilderAI.Project.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
