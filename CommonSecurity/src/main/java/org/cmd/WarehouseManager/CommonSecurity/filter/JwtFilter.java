package org.cmd.WarehouseManager.CommonSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtPrincipal;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.JwtTokenParser;
import org.cmd.WarehouseManager.CommonSecurity.token.jwt.TokenResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    @Autowired
    private TokenResolver tokenResolver;
    @Autowired
    private JwtTokenParser tokenParser;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = tokenResolver.resolveToken((HttpServletRequest) request);
        if (token != null && !token.isBlank()) {
            JwtPrincipal principal = tokenParser.parse(token);
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
