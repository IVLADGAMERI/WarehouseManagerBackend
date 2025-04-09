package org.cmd.WarehouseManager.CommonSecurity.config;

import org.cmd.WarehouseManager.CommonSecurity.jwt.TokenResolver;
import org.cmd.WarehouseManager.CommonSecurity.filter.TokenVersionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    private final TokenVersionFilter tokenVersionFilter;

    public SecurityConfig(TokenVersionFilter tokenVersionFilter) {
        this.tokenVersionFilter = tokenVersionFilter;
    }

    @Bean
    public TokenResolver tokenResolver() {
        return new TokenResolver();
    }
}
