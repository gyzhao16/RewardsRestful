package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.exception.CustomerNotFoundException;
import com.example.rewardsrestful.exception.TransactionNotFoundException;
import com.example.rewardsrestful.impl.RewardServiceImpl;
import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.model.ResponseMessage;
import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.CustomerService;
import com.example.rewardsrestful.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private TransactionService transactionService;
    private CustomerService customerService;
    private static Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);

    public TransactionController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions() {

        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/transactions/{customerId}")
    public ResponseEntity<?> getAllTransactionsByCustomerId(@PathVariable("customerId") Long customerId) {

        Customer deletedCustomer = customerService.findCustomerById(customerId);
        if (deletedCustomer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        return new ResponseEntity<>(transactionService.getAllTransactionsByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<?> getTransactionByTransactionId(@PathVariable("transactionId") Long transactionId) {

        Transaction transaction = transactionService.getTransactionByTransactionId(transactionId);
        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction not found.");
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@Validated @RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(new ResponseMessage("Transaction created.", createdTransaction), HttpStatus.CREATED);
    }

    @PutMapping("/transaction/{transactionId}")
    public ResponseEntity<?> updateTransaction(@PathVariable("transactionId") Long transactionId, @Validated @RequestBody Transaction transaction) {

        Transaction updatedTransaction = transactionService.getTransactionByTransactionId(transactionId);
        if (updatedTransaction == null) {
            throw new TransactionNotFoundException("Transaction not found.");
        }
        updatedTransaction.setAmount(transaction.getAmount());
        updatedTransaction.setCustomerId(transaction.getCustomerId());
        updatedTransaction.setTime(transaction.getTime());

        transactionService.updateTransaction(updatedTransaction);
        return new ResponseEntity<>(new ResponseMessage("Transaction updated.", updatedTransaction), HttpStatus.OK);
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionId") Long transactionId) {

        Transaction deletedTransaction = transactionService.getTransactionByTransactionId(transactionId);
        if (deletedTransaction == null) {
            throw new TransactionNotFoundException("Transaction not found.");
        }

        transactionService.deleteTransactionById(transactionId);
        return new ResponseEntity<>(new ResponseMessage("Transaction deleted."), HttpStatus.OK);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> exceptionHandlerCustomerNotFound(Exception ex) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<?> exceptionHandlerTransactionNotFound(Exception ex) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
