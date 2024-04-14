package com.clg.project.semVI.Ecommerce.Project.Repository;

import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.CartItem;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository  extends JpaRepository<CartItem , Long> {

//    @Query("SELECT ci From CartItem ci Where ci.cart =: cart And ci.product =: product And ci.size =: size And ci.user.id =: userId")
//    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product")Product product , @Param("size") String size, @Param("userId") Long userId);
//@Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.user.id = :userId")
//public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);

    public CartItem findByCartAndProductAndSizeAndUserId(Cart cart, Product product, String size, Long userId);


}
