package org.cmd.WarehouseManager.CommonTypes.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.cmd.WarehouseManager.CommonTypes.Role;

@NoArgsConstructor
@Data
public class UserAuthInfoDTO {
    private String password;
    private Role role;
    private Integer tokenVersion;
}
