package com.example.rewardsrestful.model;

import java.time.LocalDate;

public class Transaction {

    private Long id;
    private Long customerId;
    private Double amount;
    private LocalDate time;

    public Transaction(Long id, Long customerId, Double amount, LocalDate time) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
