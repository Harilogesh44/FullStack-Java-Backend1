package com.example.SwiftCheckout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckout.model.Order;
import com.example.SwiftCheckout.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
 
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }


    public Order updateOrder(Long id, Order order) {

        Order existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setCustomerId(order.getCustomerId());
            existing.setProductId(order.getProductId());
            existing.setQuantity(order.getQuantity());

            return repository.save(existing);
        }

        return null;
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}