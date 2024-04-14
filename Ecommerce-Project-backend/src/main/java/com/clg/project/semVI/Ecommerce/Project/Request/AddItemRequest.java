package com.clg.project.semVI.Ecommerce.Project.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddItemRequest {
   private  Long productId;
   private  String size;
   private  int quantity;
   private  Integer price;
}
