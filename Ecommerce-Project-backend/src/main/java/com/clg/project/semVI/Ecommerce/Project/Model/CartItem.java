package com.clg.project.semVI.Ecommerce.Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @JsonIgnore
    @ManyToOne
    private  Cart cart;

    @ManyToOne
    private  Product product;

    private  String size;

    private  int quantity;

    private  Integer price;

    private  Long discountedPrice;

    private  Long userId; 

}
