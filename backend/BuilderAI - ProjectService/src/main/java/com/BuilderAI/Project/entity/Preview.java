package com.BuilderAI.Project.entity;

import com.BuilderAI.Project.enums.PreviewStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {

    Long id;

    Project project;

    String namespace;
    String podName;
    String previewUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PreviewStatus status;

    Instant startedAt;
    Instant terminatedAt;

    Instant createdAt;

}
