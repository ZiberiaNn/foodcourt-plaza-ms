package com.pragma.powerup.domain.model.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    PENDIENTE("PENDIENTE"),
    EN_PREPARACION("EN_PREPARACION"),
    LISTO("LISTO"),
    ENTREGADO("ENTREGADO"),
    CANCELADO("CANCELADO");
    private final String name;
    StatusEnum(String name) {
        this.name = name;
    }
}