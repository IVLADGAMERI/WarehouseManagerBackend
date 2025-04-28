package com.cmd.WarehouseManager.CargoService.domain.repository;

import com.cmd.WarehouseManager.CargoService.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
