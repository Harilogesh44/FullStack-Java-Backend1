package com.example.SwiftCheckout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiftCheckout.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}