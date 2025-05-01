package com.cmd.WarehouseManager.CargoService.web.mapper;

import com.cmd.WarehouseManager.CargoService.domain.Cargo;
import com.cmd.WarehouseManager.CargoService.domain.CargoTag;
import com.cmd.WarehouseManager.CargoService.web.DTO.CargoDTO;

import java.util.List;

public class CargoMapper {
    public static List<CargoDTO> toDTO(List<Cargo> entities) {
        return entities.stream().map(CargoMapper::toDTO).toList();
    }

    public static CargoDTO toDTO(Cargo entity) {
        List<CargoTag> cargoTags = entity.getTags();
        final List<String> tags = cargoTags != null
                ? cargoTags.stream().map(CargoTag::getName).toList()
                : List.of();
        final String areaName = entity.getArea() != null
                ? entity.getArea().getName()
                : null;
        return new CargoDTO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setStatus(entity.getStatus().value)
                .setCustomerName(entity.getCustomerName())
                .setWeightInKg(entity.getWeightInKg())
                .setLengthInCm(entity.getLengthInCm())
                .setWidthInCm(entity.getWidthInCm())
                .setHeightInCm(entity.getHeightInCm())
                .setTags(tags)
                .setAreaName(areaName);
    }
}
