package com.BuilderAI.Project.dto.project.Request;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
        @NotBlank String name
) {
}
