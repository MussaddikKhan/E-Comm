package com.clg.project.semVI.Ecommerce.Project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "order_id", unique = true)
    private String orderId;

    @ManyToOne
    private  User user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate;

    private  LocalDateTime deliveryDate;

    @OneToOne
    private  Address shippingAddress;

    @Embedded
    private  PaymentDetails paymentDetails = new PaymentDetails();

    private  double totalPrice;

    private  Integer totalDiscountedPrice;

    private  Integer discount;

    private  String  orderStatus;

    private  Integer totalItems;

    @CreationTimestamp
    private  LocalDateTime createdAt;


}
