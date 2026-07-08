package com.example.SwiftCheckout.dto;

public class InventoryDTO {

    private Long id;
    private String sku;
    private Integer stockLevel;

    public InventoryDTO() {
    }

    public InventoryDTO(Long id, String sku, Integer stockLevel) {
        this.id = id;
        this.sku = sku;
        this.stockLevel = stockLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }
}