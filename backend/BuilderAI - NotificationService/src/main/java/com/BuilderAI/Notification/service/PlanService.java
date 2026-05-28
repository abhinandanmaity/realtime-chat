package com.BuilderAI.Notification.service;

import com.project.BuilderAI.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
