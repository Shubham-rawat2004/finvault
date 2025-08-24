package com.bankManagement.bank_management.controller;

import com.bankManagement.bank_management.model.Transaction;
import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.service.TransactionService;
import com.bankManagement.bank_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerService customerService;

    @Autowired
    public TransactionController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @PostMapping("/deposit/{customerId}")
    public Transaction deposit(@PathVariable Long customerId, @RequestParam double amount) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if(customer.isEmpty()) throw new RuntimeException("Customer not found");
        return transactionService.createTransaction(customer.get(), amount, "DEPOSIT");
    }
    @PostMapping("/transfer")
    public String transfer(
            @RequestParam Long fromCustomerId,
            @RequestParam Long toCustomerId,
            @RequestParam double amount) {

        // Fetch sender and receiver
        Optional<Customer> senderOpt = customerService.getCustomerById(fromCustomerId);
        Optional<Customer> receiverOpt = customerService.getCustomerById(toCustomerId);

        if (senderOpt.isEmpty() || receiverOpt.isEmpty()) {
            throw new RuntimeException("Sender or Receiver not found");
        }

        // Call the service to perform transfer
        transactionService.transferMoney(senderOpt.get(), receiverOpt.get(), amount);

        return "Transfer successful from " + senderOpt.get().getName() +
                " to " + receiverOpt.get().getName();
    }

    @PostMapping("/withdraw/{customerId}")
    public Transaction withdraw(@PathVariable Long customerId, @RequestParam double amount) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if(customer.isEmpty()) throw new RuntimeException("Customer not found");
        return transactionService.createTransaction(customer.get(), amount, "WITHDRAWAL");
    }

    @GetMapping("/customer/{customerId}")
    public List<Transaction> getCustomerTransactions(@PathVariable Long customerId) {
        return transactionService.getTransactionsByCustomer(customerId);
    }
}
