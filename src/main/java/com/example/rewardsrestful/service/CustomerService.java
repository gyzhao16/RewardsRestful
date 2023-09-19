package com.example.rewardsrestful.service;

import com.example.rewardsrestful.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
    Customer findCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(Long id);
}
