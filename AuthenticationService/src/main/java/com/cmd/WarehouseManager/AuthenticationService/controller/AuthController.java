package com.cmd.WarehouseManager.AuthenticationService.controller;

import com.cmd.WarehouseManager.AuthenticationService.service.AuthService;
import org.cmd.WarehouseManager.CommonTypes.LoginRequest;
import org.cmd.WarehouseManager.CommonTypes.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        String token = authService.login(login, password);
        response.setToken(token);
        return response;
    }
}
