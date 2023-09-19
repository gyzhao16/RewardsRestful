package com.example.rewardsrestful.impl;

import com.example.rewardsrestful.dao.CustomerRepository;
import com.example.rewardsrestful.entity.CustomerEntity;
import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.service.CustomerService;
import com.example.rewardsrestful.util.EntityVoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.rewardsrestful.util.EntityVoConverter.convertCustomerEntityToVo;
import static com.example.rewardsrestful.util.EntityVoConverter.convertCustomerVoToEntity;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(EntityVoConverter::convertCustomerEntityToVo).collect(Collectors.toList());
    }

    @Override
    public Customer findCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findById(id).orElse(null);
        return convertCustomerEntityToVo(entity);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = customerRepository.saveAndFlush(convertCustomerVoToEntity(customer));
        return convertCustomerEntityToVo(entity);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity entity = customerRepository.saveAndFlush(convertCustomerVoToEntity(customer));
        return convertCustomerEntityToVo(entity);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
