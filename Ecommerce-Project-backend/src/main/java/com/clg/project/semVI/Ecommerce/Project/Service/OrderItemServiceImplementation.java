package com.clg.project.semVI.Ecommerce.Project.Service;

import com.clg.project.semVI.Ecommerce.Project.Model.OrderItem;
import com.clg.project.semVI.Ecommerce.Project.Repository.OrderItemRepository;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class OrderItemServiceImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
