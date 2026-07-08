package com.example.SwiftCheckout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckout.model.Customer;
import com.example.SwiftCheckout.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(
            Long id,
            Customer customer) {

        Customer existing =
                repository.findById(id).orElse(null);

        if (existing != null) {

            existing.setName(
                    customer.getName()
            );

            existing.setEmail(
                    customer.getEmail()
            );

            existing.setUsername(
                    customer.getUsername()
            );

            existing.setPassword(
                    customer.getPassword()
            );

            existing.setRole(
                    customer.getRole()
            );

            return repository.save(existing);
        }

        return null;
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    public Customer findByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElse(null);
    }

    public boolean usernameExists(String username) {
        return repository.existsByUsername(username);
    }
} 