package com.cmd.WarehouseManager.AuthenticationService.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cmd.WarehouseManager.CommonTypes.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Validated
public class JwtProvider {

    private final SecretKey key;
    private final int expirationDays;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.expiration-days}") int expirationDays) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationDays = expirationDays;
    }

    public String generateToken(@NotNull @NotBlank String login,
                                @NotNull Role role,
                                @NotNull Integer tokenVersion) {
        LocalDateTime now = LocalDateTime.now();
        Instant expirationInstant = now.plusDays(expirationDays).atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expirationInstant);
        return Jwts.builder()
                .subject(login)
                .claim("role", role.name())
                .claim("token_version", tokenVersion)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
}
