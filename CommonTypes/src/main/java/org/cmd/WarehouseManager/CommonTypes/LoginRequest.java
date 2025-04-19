package org.cmd.WarehouseManager.CommonTypes;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginRequest {
    private String login;
    private String password;
}
