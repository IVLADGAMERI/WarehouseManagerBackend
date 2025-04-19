package com.cmd.WarehouseManager.UserService.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cmd.WarehouseManager.CommonTypes.Role;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false, name = "token_version")
    private int tokenVersion;
}
