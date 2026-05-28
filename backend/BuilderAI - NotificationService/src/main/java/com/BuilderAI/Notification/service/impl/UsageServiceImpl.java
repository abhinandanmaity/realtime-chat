package com.BuilderAI.Notification.service.impl;

import com.project.BuilderAI.dto.subscription.PlanLimitsResponse;
import com.project.BuilderAI.dto.subscription.UsageTodayResponse;
import com.project.BuilderAI.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
