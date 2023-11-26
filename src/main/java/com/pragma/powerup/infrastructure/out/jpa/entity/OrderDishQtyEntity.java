package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_dish_qty_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class OrderDishQtyEntity {

    @EmbeddedId
    private OrderDishQtyKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private DishEntity dish;

    @Column(name = "dish_qty")
    private Integer dishQuantity;

}