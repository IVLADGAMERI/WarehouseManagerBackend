package com.cmd.WarehouseManager.CargoService.web.DTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Setter
@Accessors(chain = true)
public class CargoDTO {
    public long id;
    public String name;
    public String status;
    public String customerName;
    public int weightInKg;
    public int lengthInCm;
    public int widthInCm;
    public int heightInCm;
    public List<String> tags;
    public String areaName;
}
