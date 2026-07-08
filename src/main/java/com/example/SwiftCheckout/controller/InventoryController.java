package com.example.SwiftCheckout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiftCheckout.model.Inventory;
import com.example.SwiftCheckout.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return service.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(
            @PathVariable Long id
    ) {
        return service.getInventoryById(id);
    }

    @PostMapping
    public Inventory addInventory(
            @RequestBody Inventory inventory
    ) {
        return service.saveInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory inventory
    ) {
        return service.updateInventory(
                id,
                inventory
        );
    }
}