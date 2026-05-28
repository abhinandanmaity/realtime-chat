package com.BuilderAI.Billing.service;


import com.project.BuilderAI.dto.auth.AuthResponse;
import com.project.BuilderAI.dto.auth.LoginRequest;
import com.project.BuilderAI.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
