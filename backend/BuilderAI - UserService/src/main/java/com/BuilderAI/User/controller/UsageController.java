package com.BuilderAI.User.controller;

import com.BuilderAI.User.dto.subscription.PlanLimitsResponse;
import com.BuilderAI.User.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity.BodyBuilder getTodayUsage() {
//        ResponseEntity<UsageTodayResponse>
//        Long userId = 1L;
//        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId));
        System.out.println("Successfull ----------->>>>>>>>>>");
        return ResponseEntity.ok();
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimits() {
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }
}
