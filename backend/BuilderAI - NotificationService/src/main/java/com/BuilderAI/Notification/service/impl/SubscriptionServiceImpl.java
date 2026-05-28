package com.BuilderAI.Notification.service.impl;

import com.project.BuilderAI.dto.subscription.CheckoutRequest;
import com.project.BuilderAI.dto.subscription.CheckoutResponse;
import com.project.BuilderAI.dto.subscription.PortalResponse;
import com.project.BuilderAI.dto.subscription.SubscriptionResponse;
import com.project.BuilderAI.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
