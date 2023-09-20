package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void testValidCustomerId() throws Exception {

        Customer customer = new Customer(1L, "Alex", "alex@gmail.com");
        when(customerService.findCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Alex")))
                .andExpect(jsonPath("$.email", Matchers.is("alex@gmail.com")));
        verify(customerService, times(1)).findCustomerById(1L);
    }

    @Test
    void testInvalidCustomerId() throws Exception {

        when((customerService.findCustomerById(100L))).thenReturn(null);

        mockMvc.perform(get("/api/customer/100"))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).findCustomerById(100L);
    }

    @Test
    void testCreateCustomer() throws Exception {

        Customer customer = new Customer(1L, "Alex", "alex@gmail.com");
        when(customerService.createCustomer(any(Customer.class)))
                .thenReturn(customer);

        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":10,\"name\":\"Alex\",\"email\":\"alex@gmail.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(customerService, times(1)).createCustomer(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        Customer customer = new Customer(1L, "Alex", "alex@gmail.com");
        when(customerService.findCustomerById(1L))
                .thenReturn(customer);

        mockMvc.perform(delete("/api/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService, times(1)).deleteCustomerById(1L);
    }
}
