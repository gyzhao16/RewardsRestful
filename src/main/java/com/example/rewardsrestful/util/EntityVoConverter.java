package com.example.rewardsrestful.util;

import com.example.rewardsrestful.entity.CustomerEntity;
import com.example.rewardsrestful.entity.TransactionEntity;
import com.example.rewardsrestful.model.Customer;
import com.example.rewardsrestful.model.Transaction;

public class EntityVoConverter {

    public static Customer convertCustomerEntityToVo(CustomerEntity entity) {

        if (entity == null) {
            return null;
        }
        return new Customer(entity.getId(), entity.getName(), entity.getEmail());
    }

    public static CustomerEntity convertCustomerVoToEntity(Customer customer) {

        if (customer == null) {
            return null;
        }
        return new CustomerEntity(customer.getId(), customer.getName(), customer.getEmail());
    }

    public static Transaction convertTransactionEntityToVo(TransactionEntity entity) {

        if (entity == null) {
            return null;
        }
        return new Transaction(entity.getId(), entity.getCustomerId(), entity.getAmount(), entity.getTime());
    }

    public static TransactionEntity convertTransactionVoToEntity(Transaction transaction) {

        if (transaction == null) {
            return null;
        }
        return new TransactionEntity(transaction.getId(), transaction.getCustomerId(), transaction.getAmount(), transaction.getTime());
    }
}
