package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.model.Transaction;
import com.example.rewardsrestful.service.CustomerService;
import com.example.rewardsrestful.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private CustomerService customerService;

    @Test
    void testGetTransactionByTransactionId() throws Exception {

        Transaction transaction = new Transaction(1L, 1L, 100.0, LocalDate.of(2023, 7, 4));
        when(transactionService.getTransactionByTransactionId(1L))
                .thenReturn(transaction);

        mockMvc.perform(get("/api/transaction/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.time").value("2023-07-04"));
        verify(transactionService, times(1)).getTransactionByTransactionId(1L);
    }

    @Test
    void testGetAllTransactions() throws Exception {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, 1L, 100.0, LocalDate.of(2023, 7, 4)),
                new Transaction(2L, 2L, 50.0, LocalDate.of(2023, 9, 11))
        );
        when(transactionService.getAllTransactions())
                .thenReturn(transactions);

        mockMvc.perform(get("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].customerId").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].time").value("2023-07-04"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].customerId").value(2L))
                .andExpect(jsonPath("$[1].amount").value(50.0))
                .andExpect(jsonPath("$[1].time").value("2023-09-11"));
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void testCreateTransaction() throws Exception {

        Transaction createdTransaction = new Transaction(1L, 1L, 100.0, LocalDate.of(2023, 7, 4));
        when(transactionService.createTransaction(any(Transaction.class)))
                .thenReturn(createdTransaction);

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"customerId\":1,\"amount\":100.0,\"time\":\"2023-07-04\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(transactionService, times(1)).createTransaction(any(Transaction.class));
    }

    @Test
    void testDeleteTransaction() throws Exception {

        Transaction transaction = new Transaction(1L, 1L, 100.0, LocalDate.of(2023, 7, 4));
        when(transactionService.getTransactionByTransactionId(1L))
                .thenReturn(transaction);

        mockMvc.perform(delete("/api/transaction/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(transactionService, times(1)).deleteTransactionById(1L);
    }
}