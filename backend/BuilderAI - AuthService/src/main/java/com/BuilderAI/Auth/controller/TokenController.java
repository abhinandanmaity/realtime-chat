package com.BuilderAI.Auth.controller;

import com.BuilderAI.Auth.dto.auth.Request.RefreshTokenRequest;
import com.BuilderAI.Auth.dto.auth.Response.RefreshTokenResponse;
import com.BuilderAI.Auth.service.impl.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TokenController {

    RefreshTokenService refreshTokenService;

    @PostMapping("/refreshToken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(refreshTokenService.refresh(request));
    }
}
