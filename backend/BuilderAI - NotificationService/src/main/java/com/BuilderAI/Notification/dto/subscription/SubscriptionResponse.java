package com.BuilderAI.Notification.dto.subscription;

import com.project.BuilderAI.dto.subscription.PlanResponse;

import java.time.Instant;

public record SubscriptionResponse(
        PlanResponse plan,
        String status,
        Instant periodEnd,
        Long tokensUsedThisCycle
) {
}
