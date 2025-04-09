package org.cmd.WarehouseManager.service;

import org.cmd.WarehouseManager.client.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenVersionService {
    @Autowired
    private UserServiceClient client;

    public Integer getTokenVersion(String username) {
        return client.getUserTokenVersion(username);
    }
}
