package com.clg.project.semVI.Ecommerce.Project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private  Long id;

     private  String title;
     private  String description;
     private  int price;
     @Column(name = "discounted_price")
     private  int discountedPrice;
    @Column(name = "discounted_persent")
     private  int discountedPercent;

    private int quantity;
    private  String brand;
    private  String color;

    @Embedded
    @ElementCollection
    private Set<Size> sizes = new HashSet<>();

    @Column(name = "image_url")
    private  String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL )
    private List<Rating>ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private  List<Review> reviews = new ArrayList<>();

    @Column(name = "num_ratings")
    private int numRatings;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt; 

}
