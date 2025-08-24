package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.RecurringPayment;

import java.util.List;
import java.util.Optional;

public interface RecurringPaymentService {

    // Create a new recurring payment
    RecurringPayment createRecurringPayment(RecurringPayment payment);

    // Get all recurring payments
    List<RecurringPayment> getAllRecurringPayments();

    // Get recurring payments for a specific payer
    List<RecurringPayment> getRecurringPaymentsByPayer(Long payerId);

    // Execute a recurring payment (transfer money from payer to receiver)
    void executeRecurringPayment(RecurringPayment payment);

    // Optional: deactivate a recurring payment
    void deactivateRecurringPayment(Long paymentId);

    // Get recurring payment by ID
    Optional<RecurringPayment> getRecurringPaymentById(Long paymentId);
}
