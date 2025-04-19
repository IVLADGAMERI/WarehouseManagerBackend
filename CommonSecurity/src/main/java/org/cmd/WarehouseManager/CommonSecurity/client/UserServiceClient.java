package org.cmd.WarehouseManager.CommonSecurity.client;

import org.cmd.WarehouseManager.CommonTypes.UpdatePasswordRequest;
import org.cmd.WarehouseManager.CommonTypes.UserAuthInfoRequest;
import org.cmd.WarehouseManager.CommonTypes.UserAuthInfoDTO;
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

    public UserAuthInfoDTO getUserAuthInfoByLogin(String login) {
        String uri = UriComponentsBuilder.fromUriString("http://USER-SERVICE/user/auth_info")
                .toUriString();
        UserAuthInfoRequest request = new UserAuthInfoRequest();
        request.setUsername(login);
        return restTemplate.postForObject(uri, request, UserAuthInfoDTO.class);
    }
}
