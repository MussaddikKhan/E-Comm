package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Exception.CartItemException;
import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.CartItem;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public  CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    // If Exisist then increase only quantity else make new cartItem; 
    public  CartItem isCartItemExist(Cart cart, Product product , String size, Long userId);

    public  void RemoveCartItem(Long userId, Long cartItemId)throws  CartItemException, UserException;

    public  CartItem findCartItemById(Long cartItemId) throws  CartItemException;




    
    
}
