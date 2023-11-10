package com.pragma.powerup.domain.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RestaurantModel {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Integer ownerUserIdentityNumber;
}
