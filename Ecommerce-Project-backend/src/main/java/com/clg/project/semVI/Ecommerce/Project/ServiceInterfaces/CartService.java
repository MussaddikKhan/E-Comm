package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.AddItemRequest;
import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;

public interface CartService {
    public Cart createCart(User user);

    public  String addCartItem(Long userId, AddItemRequest req) throws ProductExceptions;

    
     public  Cart findUserCart(Long userId);

}
