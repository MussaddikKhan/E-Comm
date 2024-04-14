package com.clg.project.semVI.Ecommerce.Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private  Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    private LocalDateTime createdAt;

    public void setReview(String review) {
    }
}
