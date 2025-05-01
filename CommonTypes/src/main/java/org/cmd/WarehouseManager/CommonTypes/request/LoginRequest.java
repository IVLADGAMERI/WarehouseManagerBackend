package org.cmd.WarehouseManager.CommonTypes.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Data
@Validated
public class LoginRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
