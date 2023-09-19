package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {

        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer) {

        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @Validated @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.findCustomerById(id);
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setId(customer.getId());
        updatedCustomer.setName(customer.getName());

        customerService.updateCustomer(updatedCustomer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {

        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
