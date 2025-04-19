package org.cmd.WarehouseManager.CommonTypes;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Data
@Validated
public class UserAuthInfoRequest {
    @NotBlank
    private String username;
}
