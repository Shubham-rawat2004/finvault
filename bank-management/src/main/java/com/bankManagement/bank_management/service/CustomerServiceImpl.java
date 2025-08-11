package com.bankManagement.bank_management.service;
import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
//        return customerRepository.(customer);
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return  null;

//        return customerRepository.findById(id)
//                .map(existingCustomer -> {
//                    existingCustomer.setName(updatedCustomer.getName());
//                    existingCustomer.setEmail(updatedCustomer.getEmail());
//                    existingCustomer.setBalance(updatedCustomer.getBalance());
//                    return customerRepository.save(existingCustomer);
//                })
//                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(Long id) {
//        customerRepository.deleteById(id);
    }
}
