package com.BuilderAI.Auth.service.impl;

import com.BuilderAI.Auth.dto.auth.Request.LoginRequest;
import com.BuilderAI.Auth.dto.auth.Response.RefreshTokenResponse;
import com.BuilderAI.Auth.dto.auth.Request.SignupRequest;
import com.BuilderAI.Auth.entity.RefreshToken;
import com.BuilderAI.Auth.entity.User;
import com.BuilderAI.Auth.error.BadRequestException;
import com.BuilderAI.Auth.mapper.UserMapper;
import com.BuilderAI.Auth.repository.UserRepository;
import com.BuilderAI.Auth.security.AuthUtil;
import com.BuilderAI.Auth.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;
    RefreshTokenService refreshTokenService;

    @Override
    public RefreshTokenResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username: " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user = userRepository.save(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());
        String jwtToken = authUtil.generateAccessToken(user);

        return new RefreshTokenResponse(jwtToken, refreshToken.getToken());
    }

    @Override
    public RefreshTokenResponse login(LoginRequest request) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            User user = (User) authentication.getPrincipal();
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
            return new RefreshTokenResponse(authUtil.generateAccessToken(user), refreshToken.getToken());
        }catch(BadRequestException ex){
            throw new BadRequestException("Username or Password is not matching" + request.username());
        }

//        } else {
//            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

//        User user = (User) authentication.getPrincipal();
//
//        String token = authUtil.generateAccessToken(user);
//        return new AuthResponse(userMapper.toUserProfileResponse(user));
    }
}
