package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Model.Address;
import com.clg.project.semVI.Ecommerce.Project.Model.Order;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Exception.OrderException;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);
    public  Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long userId);
    public  Order placedOrder(Long orderId) throws  OrderException;
    public  Order confirmedOrder(Long orderId)throws  OrderException;
    public Order shippedOrder(Long orderId)throws  OrderException;
    public  Order deliveredOrder(Long orderId) throws  OrderException;
    public  Order cancelOrder(Long orderId)throws  OrderException;

    public  List<Order> getAllOrders();

    public  void deleteOrder(Long orderId) throws  OrderException; 

}
