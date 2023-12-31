package com.job_manager.mai.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtProvider {

    @Value("${jwtSecret}")
    private String SECRET_KEY;

    public String extractUserName(String token) {
        return null;
    }

    public Claims extractAll(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).build();
    }

    public Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
