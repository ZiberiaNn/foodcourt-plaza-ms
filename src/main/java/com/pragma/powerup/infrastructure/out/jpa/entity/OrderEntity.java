package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class OrderEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false, unique = true)
    private Long id;
    private Integer clientIdentityNumber;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;
    private String status;
    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<OrderDishQtyEntity> dishQtyList;
}
