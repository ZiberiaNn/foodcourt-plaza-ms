package com.pragma.powerup.infrastructure.out.feign.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    private Long id;
    private String name;
    private String lastName;
    private Integer  identityDocument;
    private String phone;
    private Date birthDate;
    private String email;
    private Collection<RoleEntity> roles;
}
