package com.BuilderAI.Auth.service.impl;


import com.BuilderAI.Auth.dto.auth.Request.RefreshTokenRequest;
import com.BuilderAI.Auth.dto.auth.Response.RefreshTokenResponse;
import com.BuilderAI.Auth.entity.RefreshToken;
import com.BuilderAI.Auth.entity.User;
import com.BuilderAI.Auth.error.BadRequestException;
import com.BuilderAI.Auth.error.ResourceNotFoundException;
import com.BuilderAI.Auth.repository.RefreshTokenRepository;
import com.BuilderAI.Auth.repository.UserRepository;
import com.BuilderAI.Auth.security.AuthUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    UserRepository userRepository;
    AuthUtil authUtil;

    public RefreshToken createRefreshToken(String username){
        Optional<User> userInfoExtracted = userRepository.findByUsername(username);
        Optional<RefreshToken> token = refreshTokenRepository.findByUserId(userInfoExtracted.get().getId());
        if(token.isPresent()){

            refreshTokenRepository.delete(token.get());
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userInfoExtracted.get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1000L * 60 * 60 * 24 * 15)) // 15 days
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new BadRequestException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    public RefreshTokenResponse refresh(RefreshTokenRequest request) {

        return findByToken(request.token())
                .map((token) -> verifyExpiration(token))
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = authUtil.generateAccessToken(userInfo);
                    return new RefreshTokenResponse(accessToken, request.token());
                }).orElseThrow(() -> new ResourceNotFoundException("Refresh Token is not in DB..!!", request.token()));

    }
}