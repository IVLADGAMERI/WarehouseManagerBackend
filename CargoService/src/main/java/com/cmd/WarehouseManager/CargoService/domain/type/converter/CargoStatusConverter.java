package com.cmd.WarehouseManager.CargoService.domain.type.converter;

import com.cmd.WarehouseManager.CargoService.domain.type.CargoStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CargoStatusConverter implements AttributeConverter<CargoStatus, String> {
    @Override
    public String convertToDatabaseColumn(CargoStatus cargoStatus) {
        return cargoStatus.name();
    }

    @Override
    public CargoStatus convertToEntityAttribute(String name) {
        return CargoStatus.valueOf(name);
    }
}
