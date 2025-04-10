package com.cmd.WarehouseManager.UserService.data.service;

import com.cmd.WarehouseManager.UserService.data.Role;
import com.cmd.WarehouseManager.UserService.data.entity.User;
import com.cmd.WarehouseManager.UserService.data.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository repository;

    public Role getRoleByLogin(@NotNull @NotBlank String login) {
        User foundUser = getUserByLogin(login);
        return foundUser.getRole();
    }

    public Integer getTokenVersionByLogin(@NotNull @NotBlank String login) {
        User foundUser = getUserByLogin(login);
        return foundUser.getTokenVersion();
    }

    private User getUserByLogin(String login) {
        Optional<User> foundUserOptional = repository.findByLogin(login);
        if (foundUserOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователь с таким логином не найден");
        }
        return foundUserOptional.get();
    }
}
