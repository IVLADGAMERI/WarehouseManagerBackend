package org.cmd.WarehouseManager.CommonSecurity.token;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenParser {
    UserDetails parse(String token);
}
