package com.BuilderAI.Project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class TokenUtil {

    @Autowired
    private HttpServletRequest request; // ✅ inject request

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Long getCurrentUserId() {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("No token");
        }

        String token = authHeader.substring(7);

        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("userId", Long.class);
    }
}