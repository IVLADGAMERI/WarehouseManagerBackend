package com.cmd.WarehouseManager.CargoService.domain.service;

import com.cmd.WarehouseManager.CargoService.domain.Cargo;
import com.cmd.WarehouseManager.CargoService.domain.repository.CargoRepository;
import com.cmd.WarehouseManager.CargoService.web.DTO.CargoDTO;
import com.cmd.WarehouseManager.CargoService.web.mapper.CargoMapper;
import jakarta.validation.Valid;
import org.cmd.WarehouseManager.CommonTypes.request.PagedDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CargoService {
    @Autowired
    private CargoRepository repository;

    public List<CargoDTO> getAll(@Valid PagedDataRequest pagedDataRequest) {
        Sort.Direction sortDirection =
                pagedDataRequest.sortDirection > 0 ?
                        Sort.Direction.ASC :
                        Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(
                pagedDataRequest.pageIndex,
                pagedDataRequest.pageSize,
                sortDirection,
                pagedDataRequest.sortParam
        );
        List<Cargo> cargoList = repository.findAll(pageRequest).toList();
        return CargoMapper.toDTO(cargoList);
    }
}
