// TransactionService.java
package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Customer customer, double amount, String type);
    List<Transaction> getTransactionsByCustomer(Long customerId);

    // ✅ Add transfer method
    String transferMoney(Customer sender, Customer receiver, double amount);
}
