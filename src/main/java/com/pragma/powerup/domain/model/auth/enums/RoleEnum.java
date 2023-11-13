package com.pragma.powerup.domain.model.auth.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    OWNER("OWNER"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT");
    private final String name;
    private final String prefix = "ROLE_";
    RoleEnum(String name) {
        this.name = name;
    }

    public String getNameWithRolePrefix() {
        return prefix+name;
    }
}