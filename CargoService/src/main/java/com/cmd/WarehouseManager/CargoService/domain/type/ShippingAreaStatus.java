package com.cmd.WarehouseManager.CargoService.domain.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShippingAreaStatus {
    AVAILABLE("Свободна"),
    BUSY("Загружена"),
    CLOSED("Закрыта"),
    MAINTENANCE("На обслуживании");
    public final String value;
}
