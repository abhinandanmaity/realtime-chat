package com.BuilderAI.User.service.impl;

import com.BuilderAI.User.dto.subscription.PlanResponse;
import com.BuilderAI.User.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
