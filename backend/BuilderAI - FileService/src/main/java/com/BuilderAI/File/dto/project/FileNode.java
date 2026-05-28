package com.BuilderAI.File.dto.project;

import java.time.Instant;

public record FileNode(
        String path,
        Instant modifiedAt,
        Long size,
        String type
) {
}
