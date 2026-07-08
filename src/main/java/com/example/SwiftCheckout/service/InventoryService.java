package com.example.SwiftCheckout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckout.model.Inventory;
import com.example.SwiftCheckout.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public List<Inventory> getAllInventory() {
        return repository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing =
                repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        Integer adjustment =
                inventory.getStockLevel();

        if (adjustment == null) {
            return existing;
        }

        Integer currentStock =
                existing.getStockLevel() == null
                        ? 0
                        : existing.getStockLevel();

        int newStock =
                currentStock + adjustment;

        if (newStock < 0) {
            newStock = 0;
        }

        existing.setStockLevel(newStock);

        return repository.save(existing);
    }

    public void deleteInventory(Long id) {
        repository.deleteById(id);
    }
}