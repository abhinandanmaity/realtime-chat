package com.BuilderAI.User.service.impl;


import com.BuilderAI.User.dto.subscription.CheckoutRequest;
import com.BuilderAI.User.dto.subscription.CheckoutResponse;
import com.BuilderAI.User.dto.subscription.PortalResponse;
import com.BuilderAI.User.dto.subscription.SubscriptionResponse;
import com.BuilderAI.User.service.SubscriptionService;
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
