package com.cmd.WarehouseManager.AuthenticationService.service;

import com.cmd.WarehouseManager.AuthenticationService.jwt.JwtProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.cmd.WarehouseManager.CommonSecurity.client.UserServiceClient;
import org.cmd.WarehouseManager.CommonTypes.response.UserAuthInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Validated
@Slf4j
public class AuthService {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(@NotBlank String login,
                        @NotBlank String password) {
        UserAuthInfoDTO userAuthInfoDTO = getUserAuthInfoDTO(login);
        String encodedPassword = userAuthInfoDTO.getPassword();
        assertPasswordIsValid(password, encodedPassword,
                "Неверный пароль для пользователя {}",
                login);
        log.info("Успешный логин для пользователя {}", login);
        return jwtProvider.generateToken(
                login,
                userAuthInfoDTO.getRole(),
                userAuthInfoDTO.getTokenVersion()
        );
    }

    public String changePassword(@NotBlank String login,
                                 @NotBlank String oldPassword,
                                 @NotBlank String newPassword) {
        return null;

    }

    private void assertPasswordIsValid(String rawPassword,
                                       String encodedPassword,
                                       String logMessage,
                                       Object... logMessageArgs) {
        boolean isPasswordValid = passwordEncoder.matches(rawPassword, encodedPassword);
        if (!isPasswordValid) {
            log.warn(logMessage, logMessageArgs);
            throw new BadCredentialsException("Неверные данные для входа");
        }
    }

    private UserAuthInfoDTO getUserAuthInfoDTO(String login) {
        UserAuthInfoDTO userAuthInfoDTO;
        try {
            userAuthInfoDTO = userServiceClient.getUserAuthInfoByLogin(login);
        } catch (HttpClientErrorException httpClientErrorException) {
            log.error(
                    "Ошибка при запросе данных пользователя {}: {}",
                    login,
                    httpClientErrorException.getMessage());
            throw new BadCredentialsException("Пользователь с таким логином не найден");
        }
        return userAuthInfoDTO;
    }
}
