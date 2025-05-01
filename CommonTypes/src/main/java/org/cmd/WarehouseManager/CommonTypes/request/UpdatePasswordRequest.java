package org.cmd.WarehouseManager.CommonTypes.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdatePasswordRequest {
    private String login;
    private String newPassword;
}
