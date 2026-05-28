package com.BuilderAI.File.entity;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {

    Long id;

    @Column(name = "project_id", nullable = false)
    Long projectId;

    String path;

    String minioObjectKey;

    Instant createdAt;

    Instant updatedAt;

    @Column(name = "created_by")
    Long createdBy;

    @Column(name = "updated_by")
    Long updatedBy;

}
