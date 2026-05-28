package com.BuilderAI.User.service.impl;

import com.BuilderAI.User.dto.subscription.PlanLimitsResponse;
import com.BuilderAI.User.dto.subscription.UsageTodayResponse;
import com.BuilderAI.User.service.UsageService;
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
