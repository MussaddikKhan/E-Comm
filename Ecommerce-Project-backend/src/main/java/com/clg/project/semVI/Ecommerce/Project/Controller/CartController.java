package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.AddItemRequest;
import com.clg.project.semVI.Ecommerce.Project.Response.ApiResponse;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.CartService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
//@Tag(name = "Cart Management", description = "find user cart , add Item to cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt)throws UserException{
        User user   = userService.findUserProfileByJwt(jwt);
        System.out.println(user);
        Cart cart = cartService.findUserCart(user.getId()) ;
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public  ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization")String jwt ) throws  UserException, ProductExceptions {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added Successfully to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    

}
