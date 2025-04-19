package org.cmd.WarehouseManager.CommonTypes;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserAuthInfoDTO {
    private String password;
    private Role role;
    private Integer tokenVersion;
}
