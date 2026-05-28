package com.BuilderAI.Billing.service.impl;

import com.project.BuilderAI.dto.subscription.PlanResponse;
import com.project.BuilderAI.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
