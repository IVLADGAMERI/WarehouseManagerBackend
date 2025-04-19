package org.cmd.WarehouseManager.CommonSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtPrincipal;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtTokenParser;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.TokenResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private JwtTokenParser tokenParser;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = tokenResolver.resolveToken(request);
        if (token == null || token.isBlank()) {
            throw new BadCredentialsException("Не удалось авторизовать запрос");
        }
        JwtPrincipal principal = tokenParser.parse(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
