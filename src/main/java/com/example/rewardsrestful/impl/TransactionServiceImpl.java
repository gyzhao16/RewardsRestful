package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.dao.TransactionRepository;
import com.example.rewardsrestful.entity.TransactionEntity;
import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.TransactionService;
import com.example.rewardsrestful.util.EntityVoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rewardsrestful.util.EntityVoConverter.convertTransactionEntityToVo;
import static com.example.rewardsrestful.util.EntityVoConverter.convertTransactionVoToEntity;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) {
        List<TransactionEntity> transactions = transactionRepository.findTransactionEntitiesByCustomerId(customerId);
        return transactions.stream().map(EntityVoConverter::convertTransactionEntityToVo).collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionByTransactionId(Long transactionId) {
        TransactionEntity entity = transactionRepository.findById(transactionId).orElse(null);
        return convertTransactionEntityToVo(entity);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity entity = transactionRepository.saveAndFlush(convertTransactionVoToEntity(transaction));
        return convertTransactionEntityToVo(entity);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        TransactionEntity entity = transactionRepository.saveAndFlush(convertTransactionVoToEntity(transaction));
        return convertTransactionEntityToVo(entity);
    }

    @Override
    public void deleteTransactionById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
