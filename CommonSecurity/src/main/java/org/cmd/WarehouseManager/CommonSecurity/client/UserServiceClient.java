package org.cmd.WarehouseManager.CommonSecurity.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    public Integer getUserTokenVersion(String username) {
        String uri = UriComponentsBuilder.fromUriString("http://USER-SERVICE/user/token_version")
                .queryParam("username", username)
                .toUriString();
        return restTemplate.getForObject(uri, Integer.class);
    }
}
