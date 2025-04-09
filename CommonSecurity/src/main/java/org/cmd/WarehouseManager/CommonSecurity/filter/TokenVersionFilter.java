package org.cmd.WarehouseManager.CommonSecurity.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cmd.WarehouseManager.CommonSecurity.jwt.TokenParser;
import org.cmd.WarehouseManager.CommonSecurity.jwt.TokenResolver;
import org.cmd.WarehouseManager.CommonSecurity.service.TokenVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class TokenVersionFilter extends OncePerRequestFilter {
    @Autowired
    private TokenVersionService tokenVersionService;
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private TokenParser tokenParser;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = tokenResolver.resolveToken(request);
        if (token != null && !token.isEmpty()) {
            try {
                Claims claims = tokenParser.parse(token);
                String username = claims.getSubject();
                Integer tokenVersion = claims.get("token_version", Integer.class);
                Integer actualTokenVersion = tokenVersionService.getTokenVersion(username);
                if (!Objects.equals(tokenVersion, actualTokenVersion)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен устарел");
                    return;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Неверный токен");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
