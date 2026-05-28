package com.BuilderAI.Project.dto.member.Request;

import com.BuilderAI.Project.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
