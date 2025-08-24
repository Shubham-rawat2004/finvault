package com.bankManagement.bank_management.controller;

import com.bankManagement.bank_management.model.RecurringPayment;
import com.bankManagement.bank_management.service.RecurringPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-payments")
public class RecurringPaymentController {

    private final RecurringPaymentService recurringPaymentService;

    @Autowired
    public RecurringPaymentController(RecurringPaymentService recurringPaymentService) {
        this.recurringPaymentService = recurringPaymentService;
    }

    // Create a new recurring payment
    @PostMapping
    public RecurringPayment createRecurringPayment(@RequestBody RecurringPayment payment) {
        return recurringPaymentService.createRecurringPayment(payment);
    }

    // Get all recurring payments
    @GetMapping
    public List<RecurringPayment> getAllRecurringPayments() {
        return recurringPaymentService.getAllRecurringPayments();
    }

    // Get all active recurring payments for a specific payer
    @GetMapping("/payer/{payerId}")
    public List<RecurringPayment> getRecurringPaymentsByPayer(@PathVariable Long payerId) {
        return recurringPaymentService.getRecurringPaymentsByPayer(payerId);
    }

    // Execute a recurring payment manually
    @PostMapping("/execute/{paymentId}")
    public String executeRecurringPayment(@PathVariable Long paymentId) {
        RecurringPayment payment = recurringPaymentService.getRecurringPaymentById(paymentId)
                .orElseThrow(() -> new RuntimeException("Recurring Payment not found"));
        recurringPaymentService.executeRecurringPayment(payment);
        return "Recurring payment executed successfully";
    }

    // Deactivate a recurring payment
    @PostMapping("/deactivate/{paymentId}")
    public String deactivateRecurringPayment(@PathVariable Long paymentId) {
        recurringPaymentService.deactivateRecurringPayment(paymentId);
        return "Recurring payment deactivated successfully";
    }
}
