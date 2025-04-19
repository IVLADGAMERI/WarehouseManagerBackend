package org.cmd.WarehouseManager.CommonSecurity.token.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.cmd.WarehouseManager.CommonSecurity.token.TokenParser;
import org.cmd.WarehouseManager.CommonTypes.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenParser implements TokenParser {
    private final SecretKey key;

    public JwtTokenParser(@Value("${jwt.secret}") String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(secretBytes);
    }

    public JwtPrincipal parse(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        Role role = claims.get("role", Role.class);
        Integer tokenVersion = claims.get("token_version", Integer.class);
        return new JwtPrincipal(username, role, tokenVersion);
    }
}
