package com.BuilderAI.APIGateway.util;

import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
//@RequiredArgsConstructor
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtUtil {

    @Value("${jwt.secret-key}")
    String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public void validateToken(final String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);

            try {

                System.out.println("Token: " + token);
                System.out.println("Secret Length: " + jwtSecretKey.length());

                Claims claims = Jwts.parser()
                        .verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                System.out.println("✅ VALID TOKEN");
                System.out.println("Username: " + claims.getSubject());
                System.out.println("UserId: " + claims.get("userId"));

            } catch (Exception e) {

                System.out.println("❌ ERROR: " + e.getClass().getName());
                System.out.println("❌ MESSAGE: " + e.getMessage());
                throw new RuntimeException("Invalid token");
            }
        }

//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}