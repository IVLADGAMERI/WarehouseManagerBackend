package com.cmd.WarehouseManager.CargoService.domain;

import com.cmd.WarehouseManager.CargoService.domain.type.ShippingAreaStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity(name = "shipping_areas")
public class ShippingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private ShippingAreaStatus status;
    @Column(name = "last_inspection_date")
    private LocalDate lastInspectionDate;
}
