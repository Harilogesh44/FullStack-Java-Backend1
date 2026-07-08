package com.example.SwiftCheckout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckout.model.Payment;
import com.example.SwiftCheckout.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Payment savePayment(Payment payment) {
        return repository.save(payment);
    }

    public Payment updatePayment(Long id, Payment payment) {

        Payment existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setPaymentMethod(payment.getPaymentMethod());
            existing.setAmount(payment.getAmount());

            return repository.save(existing);
        }

        return null;
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}