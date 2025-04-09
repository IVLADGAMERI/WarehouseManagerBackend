package org.cmd.WarehouseManager.config;

import org.cmd.WarehouseManager.jwt.TokenResolver;
import org.cmd.WarehouseManager.filter.TokenVersionFilter;
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
