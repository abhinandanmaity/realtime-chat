package com.BuilderAI.Notification.dto.member;

import com.project.BuilderAI.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
