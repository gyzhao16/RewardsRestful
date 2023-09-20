package com.example.rewardsrestful.dao;

import com.example.rewardsrestful.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findTransactionEntitiesByCustomerId(long customerId);
    List<TransactionEntity> findTransactionEntitiesByCustomerIdAndTimeBetween(Long customerId, LocalDate from, LocalDate to);
}
