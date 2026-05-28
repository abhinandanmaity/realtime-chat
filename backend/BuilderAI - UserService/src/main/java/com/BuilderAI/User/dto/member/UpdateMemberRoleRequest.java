package com.BuilderAI.User.dto.member;

import com.BuilderAI.User.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
