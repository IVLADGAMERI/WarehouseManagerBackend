package com.cmd.WarehouseManager.UserService.data.repository;

import com.cmd.WarehouseManager.UserService.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
