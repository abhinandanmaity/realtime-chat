package com.BuilderAI.Project.entity;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSession {

    Project project;

    @Column(name = "user_id", nullable = false)
    Long userId;

    String title;

    Instant createdAt;
    Instant updatedAt;

    Instant deletedAt; //soft delete
}
