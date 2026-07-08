package com.example.SwiftCheckout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckout.model.Product;
import com.example.SwiftCheckout.model.Inventory;
import com.example.SwiftCheckout.repository.ProductRepository;
import com.example.SwiftCheckout.repository.InventoryRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        Product savedProduct = repository.save(product);
        if (product.getSku() != null) {
            String sku = product.getSku().trim();
            if (!inventoryRepository.findBySku(sku).isPresent()) {
                inventoryRepository.save(new Inventory(null, sku, 0));
            }
        }
        return savedProduct;
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = repository.findById(id).orElse(null);

        if (existing != null) {
            String oldSku = existing.getSku();
            existing.setName(product.getName());
            existing.setSku(product.getSku());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());

            Product updated = repository.save(existing);

            if (product.getSku() != null) {
                String newSku = product.getSku().trim();
                if (!newSku.equals(oldSku)) {
                    Inventory inv = inventoryRepository.findBySku(oldSku).orElse(null);
                    if (inv != null) {
                        inv.setSku(newSku);
                        inventoryRepository.save(inv);
                    } else if (!inventoryRepository.findBySku(newSku).isPresent()) {
                        inventoryRepository.save(new Inventory(null, newSku, 0));
                    }
                }
            }

            return updated;
        }

        return null;
    }

    public void deleteProduct(Long id) {
        Product existing = repository.findById(id).orElse(null);
        if (existing != null) {
            String sku = existing.getSku();
            repository.deleteById(id);
            if (sku != null) {
                inventoryRepository.findBySku(sku).ifPresent(inv -> inventoryRepository.delete(inv));
            }
        }
    }
}