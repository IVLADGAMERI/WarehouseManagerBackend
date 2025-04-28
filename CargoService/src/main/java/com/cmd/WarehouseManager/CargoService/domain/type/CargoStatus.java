package com.cmd.WarehouseManager.CargoService.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CargoStatus {
    STORED("На хранении"), ORDERED("Заказан");
    public final String value;
}
