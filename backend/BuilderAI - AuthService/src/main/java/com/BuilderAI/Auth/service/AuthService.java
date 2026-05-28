package com.BuilderAI.Auth.service;


import com.BuilderAI.Auth.dto.auth.Request.LoginRequest;
import com.BuilderAI.Auth.dto.auth.Response.RefreshTokenResponse;
import com.BuilderAI.Auth.dto.auth.Request.SignupRequest;

public interface AuthService {
    RefreshTokenResponse signup(SignupRequest request);

    RefreshTokenResponse login(LoginRequest request);
}
