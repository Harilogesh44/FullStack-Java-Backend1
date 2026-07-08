package com.example.SwiftCheckout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiftCheckout.model.Customer;
import com.example.SwiftCheckout.security.JwtProvider;
import com.example.SwiftCheckout.service.CustomerService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private CustomerService service;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody Customer customer) {

        if (customer.getName() == null ||
                customer.getName().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Name is required");
        }

        if (customer.getEmail() == null ||
                customer.getEmail().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email is required");
        }

        if (customer.getUsername() == null ||
                customer.getUsername().trim().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Username is required");
        }

        if (customer.getPassword() == null ||
                customer.getPassword().isEmpty()) {

            return ResponseEntity
                    .badRequest()
                    .body("Password is required");
        }

        if (service.usernameExists(
                customer.getUsername())) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }

        customer.setRole("USER");

        service.saveCustomer(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Customer customer) {

        if (customer.getUsername() == null ||
                customer.getPassword() == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Username and password are required"
                    );
        }

        Customer existingCustomer =
                service.findByUsername(
                        customer.getUsername()
                );

        if (existingCustomer != null &&
                existingCustomer
                        .getPassword()
                        .equals(customer.getPassword())) {

            String token =
                    jwtProvider.generateToken(
                            existingCustomer.getUsername()
                    );

            return ResponseEntity.ok(token);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        "Invalid Username or Password"
                );
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        Customer customer =
                service.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer updatedCustomer =
                service.updateCustomer(
                        id,
                        customer
                );

        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long id) {

        Customer customer =
                service.getCustomerById(id);

        if (customer == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Customer not found");
        }

        service.deleteCustomer(id);

        return ResponseEntity.ok(
                "Customer Deleted Successfully"
        );
    }
}