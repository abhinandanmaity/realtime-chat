package com.BuilderAI.User.entity;

import jakarta.persistence.Column;

import java.time.Instant;

public class UsageLog {

    Long id;
    User user;
    @Column(name = "project_id", nullable = false)
    Long projectId;

    String action;

    Integer tokensUsed;
    Integer durationMs;

    String metaData; // JSON of {model_used, prompt_used},

    Instant createdAt;
}
