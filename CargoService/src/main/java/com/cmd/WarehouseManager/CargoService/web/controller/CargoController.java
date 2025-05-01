package com.cmd.WarehouseManager.CargoService.web.controller;

import com.cmd.WarehouseManager.CargoService.domain.service.CargoService;
import com.cmd.WarehouseManager.CargoService.web.DTO.CargoDTO;
import org.cmd.WarehouseManager.CommonTypes.request.PagedDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CargoController {
    @Autowired
    private CargoService service;

    @GetMapping("cargo/")
    public List<CargoDTO> getAll(@ModelAttribute PagedDataRequest pagedDataRequest) {
        return service.getAll(pagedDataRequest);
    }
}
