package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class RestaurantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String nit;
    @Column(length = 50)
    private String address;
    @Column(length = 13)
    private String phone;
    private String urlLogo;
    private Integer ownerUserIdentityNumber;
}
