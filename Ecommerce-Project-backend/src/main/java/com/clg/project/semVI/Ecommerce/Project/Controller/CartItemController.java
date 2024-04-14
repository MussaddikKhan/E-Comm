package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.CartItemException;
import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.CartItem;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.AddItemRequest;
import com.clg.project.semVI.Ecommerce.Project.Response.ApiResponse;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.CartItemService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> RemoveCartItem(@PathVariable Long id, @RequestHeader("Authorization")String jwt ) throws UserException, ProductExceptions, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.RemoveCartItem(user.getId(), id);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Removed Successfully from cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem, @RequestHeader("Authorization")String jwt) throws CartItemException, UserException{

        User user=userService.findUserProfileByJwt(jwt);

        CartItem updatedCartItem =cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        //ApiResponse res=new ApiResponse("Item Updated",true);

        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
    }

}
