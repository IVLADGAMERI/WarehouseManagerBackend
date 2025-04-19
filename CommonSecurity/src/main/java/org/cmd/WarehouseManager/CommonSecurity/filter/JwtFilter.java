package org.cmd.WarehouseManager.CommonSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cmd.WarehouseManager.CommonSecurity.service.TokenVersionService;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtPrincipal;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtTokenParser;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.TokenResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends GenericFilterBean {
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private JwtTokenParser tokenParser;
    @Autowired
    private TokenVersionService tokenVersionService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = tokenResolver.resolveToken((HttpServletRequest) request);
        if (token != null && !token.isBlank()) {
            JwtPrincipal principal = tokenParser.parse(token);
            String username = principal.getUsername();
            Integer tokenVersion = principal.getTokenVersion();
            Integer actualTokenVersion = tokenVersionService.getTokenVersion(username);
            if (!Objects.equals(tokenVersion, actualTokenVersion)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Токен устарел");
                return;
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            principal.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
