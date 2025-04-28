package com.cmd.WarehouseManager.CargoService.domain;

import com.cmd.WarehouseManager.CargoService.domain.type.CargoStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private CargoArea area;
    private String name;
    private String code;
    private CargoStatus status;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "weight_in_kg")
    private int weightInKg;
    @Column(name = "length_in_cm")
    private int lengthInCm;
    @Column(name = "width_in_cm")
    private int widthInCm;
    @Column(name = "height_in_cm")
    private int heightInCm;
    @ManyToMany
    @JoinTable(
            name = "cargo_tags",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<CargoTag> tags;
}
