package org.cmd.WarehouseManager.CommonSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtPrincipal;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtTokenParser;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.TokenResolver;
import org.cmd.WarehouseManager.CommonSecurity.service.TokenVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtTokenVersionFilter extends OncePerRequestFilter {
    @Autowired
    private TokenVersionService tokenVersionService;
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private JwtTokenParser tokenParser;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = tokenResolver.resolveToken(request);
        if (token != null && !token.isEmpty()) {
            try {
                JwtPrincipal principal = tokenParser.parse(token);
                String username = principal.getUsername();
                Integer tokenVersion = principal.getTokenVersion();
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
