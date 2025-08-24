package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer updateCustomer(Long id,Customer customer);
    void deleteCustomer(Long id);
    List<Customer> searchCustomerByName(String name);
    Optional<Customer> getCustomerByEmail(String email);

Customer deposit (Long customerId, double amount);

    void saveCustomer(Customer customer);

    Customer withdraw (Long customerId, double amount);
void transfer(Long fromCustomerId, Long toCustomerId,double amount);


    Page<Customer> getAllCustomersPaginated(Pageable pageable);


}
