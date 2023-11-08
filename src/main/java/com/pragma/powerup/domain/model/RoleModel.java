package com.pragma.powerup.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoleModel {
    private Long id;
    private String name;
    @Getter
    public enum RoleEnum {
        ADMIN(1L, "ADMIN"),
        OWNER(2L, "OWNER"),
        EMPLOYEE(3L, "EMPLOYEE"),
        CLIENT(4L, "CLIENT");

        private final Long id;
        private final String name;

        RoleEnum(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}

