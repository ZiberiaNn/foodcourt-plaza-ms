package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderDishQtyKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "dish_id")
    private Long dishId;

}