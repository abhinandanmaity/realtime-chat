package com.BuilderAI.User.service;


import com.BuilderAI.User.dto.subscription.PlanLimitsResponse;
import com.BuilderAI.User.dto.subscription.UsageTodayResponse;

public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
