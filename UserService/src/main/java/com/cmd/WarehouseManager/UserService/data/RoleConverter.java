package com.cmd.WarehouseManager.UserService.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cmd.WarehouseManager.CommonTypes.Role;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Stream.of(Role.values())
                .filter(item -> item.getCode().equals(s))
                .findFirst()
                .orElse(null);
    }
}
