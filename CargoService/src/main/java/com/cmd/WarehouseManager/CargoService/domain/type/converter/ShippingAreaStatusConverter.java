package com.cmd.WarehouseManager.CargoService.domain.type.converter;

import com.cmd.WarehouseManager.CargoService.domain.type.ShippingAreaStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ShippingAreaStatusConverter implements AttributeConverter<ShippingAreaStatus, String> {
    @Override
    public String convertToDatabaseColumn(ShippingAreaStatus shippingAreaStatus) {
        return shippingAreaStatus.name();
    }

    @Override
    public ShippingAreaStatus convertToEntityAttribute(String name) {
        return ShippingAreaStatus.valueOf(name);
    }
}
