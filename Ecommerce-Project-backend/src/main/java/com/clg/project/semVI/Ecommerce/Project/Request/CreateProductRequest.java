package com.clg.project.semVI.Ecommerce.Project.Request;

import com.clg.project.semVI.Ecommerce.Project.Model.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String title;
    private  String description;
    private  int price;
    private  int discountedPrice;
    private  int discountedPercent;

    private int percent;
    private int quantity;
    private  String brand;
    private String color;
    private Set<Size>sizes = new HashSet<>();
    private  String imageUrl;

    // For Category Apis  -> Men/ Clothing/ Mens Shirt  So that's why we use three categories
    private  String topLevelCategory;
    private  String secondLevelCategory;
    private  String thirdLevelCategory;
}
