package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getAllTransactionsByCustomerId(@PathVariable("id") Long customerId) {

        return new ResponseEntity<>(transactionService.getAllTransactionsByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<?> getTransactionByTransactionId(@PathVariable("id") Long transactionId) {

        return new ResponseEntity<>(transactionService.getTransactionByTransactionId(transactionId), HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@Validated @RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("id") Long transactionId, @Validated @RequestBody Transaction transaction) {

        Transaction updatedTransaction = transactionService.getTransactionByTransactionId(transactionId);
        updatedTransaction.setAmount(transaction.getAmount());
        updatedTransaction.setCustomerId(transaction.getCustomerId());
        updatedTransaction.setTime(transaction.getTime());

        transactionService.updateTransaction(updatedTransaction);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long transactionId) {

        transactionService.deleteTransactionById(transactionId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
