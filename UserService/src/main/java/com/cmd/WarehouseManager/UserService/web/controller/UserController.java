package com.cmd.WarehouseManager.UserService.web.controller;

import com.cmd.WarehouseManager.UserService.data.service.UserService;
import org.cmd.WarehouseManager.CommonTypes.UserAuthInfoDTO;
import org.cmd.WarehouseManager.CommonTypes.UserAuthInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("user/token_version")
    public Integer getUserTokenVersion(@RequestParam String login) {
        return service.getTokenVersionByLogin(login);
    }

    @PostMapping("user/auth_info")
    public UserAuthInfoDTO getUserAuthInfoByLogin(@RequestBody UserAuthInfoRequest requestBody) {
        String login = requestBody.getUsername();
        return service.getUserAuthInfoByLogin(login);
    }
}
