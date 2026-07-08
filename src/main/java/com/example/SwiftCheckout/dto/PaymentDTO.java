package com.example.SwiftCheckout.dto;

public class PaymentDTO {

    private Long id;
    private String paymentMethod;
    private Double amount;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, String paymentMethod, Double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}