package com.bankManagement.bank_management.repository;

import com.bankManagement.bank_management.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    // Constant list of customers (mock data)
    private static final List<Customer> CUSTOMERS = Arrays.asList(
            new Customer(1L, "John Doe", "john@example.com", 5000.0),
            new Customer(2L, "Jane Smith", "jane@example.com", 7500.0),
            new Customer(3L, "Bob Johnson", "bob@example.com", 3000.0)
    );

    public List<Customer> findAll() {
        return CUSTOMERS;
    }

    public Optional<Customer> findById(Long id) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public Optional<Customer> findByEmail(String email) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean existsByEmail(String email) {
        return CUSTOMERS.stream()
                .anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
}
