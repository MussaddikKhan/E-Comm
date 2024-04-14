package com.clg.project.semVI.Ecommerce.Project.Repository;

import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {


     // Check All Carts User and then there ids if match return that cart
//    @Query("Select c From Cart c Where c.user.id =: userId")
    public Cart findByUserId(Long userId);
}
