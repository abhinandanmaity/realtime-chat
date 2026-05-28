package com.BuilderAI.User.service;


import com.BuilderAI.User.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
