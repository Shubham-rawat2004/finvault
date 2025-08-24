package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.model.Transaction;
import com.bankManagement.bank_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CustomerService customerService) {
        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
    }

    @Override
    public Transaction createTransaction(Customer customer, double amount, String type) {
        if (type.equals("WITHDRAWAL") && customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        if (type.equals("DEPOSIT")) {
            customer.setBalance(customer.getBalance() + amount);
        } else if (type.equals("WITHDRAWAL")) {
            customer.setBalance(customer.getBalance() - amount);
        }

        // ✅ Save updated customer balance
        customerService.saveCustomer(customer);

        Transaction transaction = new Transaction(amount, type, customer);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByCustomer(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    // ✅ Transfer feature
    @Override
    public String transferMoney(Customer sender, Customer receiver, double amount) {
        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for transfer");
        }

        // Deduct from sender
        sender.setBalance(sender.getBalance() - amount);
        customerService.saveCustomer(sender);
        transactionRepository.save(new Transaction(amount, "TRANSFER_SENT", sender));

        // Add to receiver
        receiver.setBalance(receiver.getBalance() + amount);
        customerService.saveCustomer(receiver);
        transactionRepository.save(new Transaction(amount, "TRANSFER_RECEIVED", receiver));

        return "Transfer successful from " + sender.getName() + " to " + receiver.getName();
    }
}
