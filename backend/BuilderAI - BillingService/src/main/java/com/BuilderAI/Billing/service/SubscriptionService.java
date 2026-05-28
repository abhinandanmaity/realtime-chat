package com.BuilderAI.Billing.service;

import com.project.BuilderAI.dto.subscription.CheckoutRequest;
import com.project.BuilderAI.dto.subscription.CheckoutResponse;
import com.project.BuilderAI.dto.subscription.PortalResponse;
import com.project.BuilderAI.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
