package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.OrderException;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Address;
import com.clg.project.semVI.Ecommerce.Project.Model.Order;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.OrderService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization")String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);
         return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public  ResponseEntity<List<Order>>userOrderHistory(@RequestHeader("Authorization")String jwt)throws  UserException{
        User user = userService.findUserProfileByJwt(jwt);
        List<Order>orders = orderService.userOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public  ResponseEntity<Order>findOrderById(@PathVariable("Id") Long orderId, @RequestHeader("Authorization")String jwt)throws  UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order orders = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }
}
