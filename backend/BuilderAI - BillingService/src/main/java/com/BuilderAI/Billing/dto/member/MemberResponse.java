package com.BuilderAI.Billing.dto.member;

import com.project.BuilderAI.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
