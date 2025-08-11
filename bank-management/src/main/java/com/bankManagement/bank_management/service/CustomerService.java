package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer updateCustomer(Long id,Customer customer);
    void deleteCustomer(Long id);
}
