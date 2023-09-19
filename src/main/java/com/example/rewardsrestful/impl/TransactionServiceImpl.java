package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Transaction getTransactionByTransactionId(Long TransactionId) {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransactionById(Long TransactionId) {

    }
}
