package com.code.jhin.repository.orderRepository;

import com.code.jhin.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
