package com.cmd.WarehouseManager.UserService.web.controller;

import com.cmd.WarehouseManager.UserService.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("user/token_version")
    public Integer getUserTokenVersion(@RequestParam String login) {
        return service.getTokenVersionByLogin(login);
    }
}
