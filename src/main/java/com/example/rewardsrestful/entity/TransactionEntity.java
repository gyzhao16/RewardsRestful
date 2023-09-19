package com.example.rewardsrestful.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "customerid")
    private Long customerId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "time")
    private Date time;

    public TransactionEntity(Long id, Long customerId, Double amount, Date time) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.time = time;
    }

    public TransactionEntity() {

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
}
