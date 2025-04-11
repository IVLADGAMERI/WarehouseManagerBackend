package org.cmd.WarehouseManager.CommonTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    WORKER("W"), MANAGER("M");
    private final String code;
}
