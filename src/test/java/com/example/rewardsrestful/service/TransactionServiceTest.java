package com.example.rewardsrestful.service;

import com.example.rewardsrestful.dao.TransactionRepository;
import com.example.rewardsrestful.entity.TransactionEntity;
import com.example.rewardsrestful.impl.TransactionServiceImpl;
import com.example.rewardsrestful.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.rewardsrestful.util.EntityVoConverter.convertTransactionVoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    private Transaction testCase1 = new Transaction(1L, 10L, 206.0, LocalDate.of(2023, 9, 19));
    private Transaction testCase2 = new Transaction(2L, 10L, 89.0, LocalDate.of(2023, 7, 7));

    @BeforeEach
    void setUpService() {
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    void testGetTransactionByTransactionId() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.ofNullable(convertTransactionVoToEntity(testCase1)));
        assertEquals(transactionService.getTransactionByTransactionId(1L), testCase1);
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllTransactionsByCustomerId() {
        List<Transaction> expectedList = new ArrayList<>();
        expectedList.add(testCase1);
        expectedList.add(testCase2);
        List<TransactionEntity> testList = new ArrayList<>();
        testList.add(convertTransactionVoToEntity(testCase1));
        testList.add(convertTransactionVoToEntity(testCase2));
        when(transactionRepository.findTransactionEntitiesByCustomerId(10L)).thenReturn(testList);
        assertEquals(transactionService.getAllTransactionsByCustomerId(10L), expectedList);
        verify(transactionRepository, times(1)).findTransactionEntitiesByCustomerId(10L);
    }
}
