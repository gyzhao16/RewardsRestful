package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomerById(Long id) {

    }
}
