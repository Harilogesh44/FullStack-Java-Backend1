package com.example.SwiftCheckout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiftCheckout.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}