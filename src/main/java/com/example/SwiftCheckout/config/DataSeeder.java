package com.example.SwiftCheckout.config;

import com.example.SwiftCheckout.model.Product;
import com.example.SwiftCheckout.model.Inventory;
import com.example.SwiftCheckout.repository.ProductRepository;
import com.example.SwiftCheckout.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public DataSeeder(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            productRepository.save(
                new Product(
                    null,
                    "Laptop",
                    "LAP-001",
                    55000.0,
                    "ELECTRONICS"
                )
            );

            productRepository.save(
                new Product(
                    null,
                    "Mouse",
                    "MOU-001",
                    800.0,
                    "ELECTRONICS"
                )
            );

            productRepository.save(
                new Product(
                    null,
                    "Keyboard",
                    "KEY-001",
                    1500.0,
                    "ELECTRONICS"
                )
            );
        }

        if (inventoryRepository.count() == 0) {
            inventoryRepository.save(new Inventory(null, "LAP-001", 15));
            inventoryRepository.save(new Inventory(null, "MOU-001", 50));
            inventoryRepository.save(new Inventory(null, "KEY-001", 30));
        }
    }
}