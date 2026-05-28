package com.BuilderAI.Project.dto.project.Response;


import com.BuilderAI.Project.dto.user.Response.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        UserProfileResponse owner
) {
}
