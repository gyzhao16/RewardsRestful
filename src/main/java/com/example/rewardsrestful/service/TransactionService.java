package com.example.rewardsrestful.service;

import com.example.rewardsrestful.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactionsByCustomerId(Long customerId);
    Transaction getTransactionByTransactionId(Long transactionId);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(Long transactionId);
}
