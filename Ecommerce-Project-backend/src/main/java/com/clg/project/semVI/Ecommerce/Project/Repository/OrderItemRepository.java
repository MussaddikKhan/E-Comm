package com.clg.project.semVI.Ecommerce.Project.Repository;

import com.clg.project.semVI.Ecommerce.Project.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
