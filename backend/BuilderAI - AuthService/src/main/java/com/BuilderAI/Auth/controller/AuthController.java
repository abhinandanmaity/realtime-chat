package com.BuilderAI.Auth.controller;


import com.BuilderAI.Auth.dto.auth.Request.LoginRequest;
import com.BuilderAI.Auth.dto.auth.Request.SignupRequest;
import com.BuilderAI.Auth.dto.auth.Response.RefreshTokenResponse;
import com.BuilderAI.Auth.dto.auth.Response.UserProfileResponse;
import com.BuilderAI.Auth.service.AuthService;
import com.BuilderAI.Auth.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService;
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<RefreshTokenResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }

}
