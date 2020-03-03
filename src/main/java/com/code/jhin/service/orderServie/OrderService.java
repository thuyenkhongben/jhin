package com.code.jhin.service.orderServie;

import com.code.jhin.model.order.Order;
import com.code.jhin.model.product.Product;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllOrder();

    Optional<Order>findByIdOrder(Long id);

    void save(Order order);

    void remove(Long id);

}
