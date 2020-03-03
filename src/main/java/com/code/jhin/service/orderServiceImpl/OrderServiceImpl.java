package com.code.jhin.service.orderServiceImpl;

import com.code.jhin.model.order.Order;
import com.code.jhin.model.product.Product;
import com.code.jhin.repository.orderRepository.OrderRepository;
import com.code.jhin.service.orderServie.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findByIdOrder(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
    orderRepository.deleteById(id);
    }
}
