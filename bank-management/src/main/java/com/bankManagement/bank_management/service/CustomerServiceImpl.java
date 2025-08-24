package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        // clear ID so Hibernate inserts instead of merges
        customer.setId(null);
        return customerRepository.save(customer);
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
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    existingCustomer.setBalance(updatedCustomer.getBalance());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer deposit(Long customerId, double amount) {
        if (amount <= 0) throw new RuntimeException("Deposit amount must be positive");
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        customer.setBalance(customer.getBalance() + amount);
        return customerRepository.save(customer);
    }
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    @Override
    public Customer withdraw(Long customerId, double amount) {
        if (amount <= 0) throw new RuntimeException("Withdraw amount must be positive");
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        if (customer.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");
        customer.setBalance(customer.getBalance() - amount);
        return customerRepository.save(customer);
    }

    @Override
    public void transfer(Long fromCustomerId, Long toCustomerId, double amount) {
        if (amount <= 0) throw new RuntimeException("Transfer amount must be positive");
        Customer sender = customerRepository.findById(fromCustomerId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        Customer receiver = customerRepository.findById(toCustomerId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        if (sender.getBalance() < amount) throw new RuntimeException("Insufficient balance for transfer");


        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        customerRepository.save(sender);
        customerRepository.save(receiver);
    }

    @Override
    public Page<Customer> getAllCustomersPaginated(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


}
