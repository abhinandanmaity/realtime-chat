package com.BuilderAI.User.service;


import com.BuilderAI.User.dto.subscription.CheckoutRequest;
import com.BuilderAI.User.dto.subscription.CheckoutResponse;
import com.BuilderAI.User.dto.subscription.PortalResponse;
import com.BuilderAI.User.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
