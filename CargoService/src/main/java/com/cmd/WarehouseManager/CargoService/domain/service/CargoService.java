package com.cmd.WarehouseManager.CargoService.domain.service;

import com.cmd.WarehouseManager.CargoService.web.mapper.CargoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    @Autowired
    private CargoMapper cargoMapper;


}
