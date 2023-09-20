package com.example.rewardsrestful.controller;

import com.example.rewardsrestful.exception.CustomerNotFoundException;
import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.model.ResponseMessage;
import com.example.rewardsrestful.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomers() {

        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {

        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer) {

        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(new ResponseMessage("Customer created.", createdCustomer), HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @Validated @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.findCustomerById(id);
        if (updatedCustomer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setId(customer.getId());
        updatedCustomer.setName(customer.getName());

        customerService.updateCustomer(updatedCustomer);
        return new ResponseEntity<>(new ResponseMessage("Customer updated.", updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {

        Customer deletedCustomer = customerService.findCustomerById(id);
        if (deletedCustomer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }

        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> exceptionHandlerCustomerNotFound(Exception ex) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
