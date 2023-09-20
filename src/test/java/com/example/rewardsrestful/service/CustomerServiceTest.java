package com.example.rewardsrestful.service;

import com.example.rewardsrestful.dao.CustomerRepository;
import com.example.rewardsrestful.entity.CustomerEntity;
import com.example.rewardsrestful.impl.CustomerServiceImpl;
import com.example.rewardsrestful.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.rewardsrestful.util.EntityVoConverter.convertCustomerVoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private Customer testCase = new Customer(1L, "Alex", "alex@gmail.com");

    @BeforeEach
    void setUpService() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testFindCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(convertCustomerVoToEntity(testCase)));
        assertEquals(customerService.findCustomerById(1L), testCase);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllCustomers() {
        List<Customer> expectedList = new ArrayList<>();
        expectedList.add(testCase);
        List<CustomerEntity> testList = new ArrayList<>();
        testList.add(convertCustomerVoToEntity(testCase));
        when(customerRepository.findAll()).thenReturn(testList);
        assertEquals(customerService.findAllCustomers(), expectedList);
        verify(customerRepository, times(1)).findAll();
    }
}
