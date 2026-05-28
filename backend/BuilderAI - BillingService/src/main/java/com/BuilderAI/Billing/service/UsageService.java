package com.BuilderAI.Billing.service;


import com.project.BuilderAI.dto.subscription.PlanLimitsResponse;
import com.project.BuilderAI.dto.subscription.UsageTodayResponse;

public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
