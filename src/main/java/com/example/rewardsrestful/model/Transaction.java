package com.example.rewardsrestful.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Double amount;
    private Date time;

    public Transaction(Long id, Long customerId, Double amount, Date time) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
