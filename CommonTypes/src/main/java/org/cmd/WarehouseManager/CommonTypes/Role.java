package org.cmd.WarehouseManager.CommonTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_WORKER("W"), ROLE_MANAGER("M");
    private final String code;
}
