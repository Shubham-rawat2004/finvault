package com.bankManagement.bank_management.service;

import com.bankManagement.bank_management.model.RecurringPayment;
import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.repository.RecurringPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecurringPaymentServiceImpl implements RecurringPaymentService {

    private final RecurringPaymentRepository recurringPaymentRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;

    @Autowired
    public RecurringPaymentServiceImpl(RecurringPaymentRepository recurringPaymentRepository,
                                       CustomerService customerService,
                                       TransactionService transactionService) {
        this.recurringPaymentRepository = recurringPaymentRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @Override
    public RecurringPayment createRecurringPayment(RecurringPayment payment) {
        payment.setActive(true); // always active initially
        return recurringPaymentRepository.save(payment);
    }

    @Override
    public List<RecurringPayment> getAllRecurringPayments() {
        return recurringPaymentRepository.findAll();
    }

    @Override
    public List<RecurringPayment> getRecurringPaymentsByPayer(Long payerId) {
        return recurringPaymentRepository.findByPayerIdAndActiveTrue(payerId);
    }

    @Override
    public void executeRecurringPayment(RecurringPayment payment) {
        Optional<Customer> payerOpt = customerService.getCustomerById(payment.getPayer().getId());
        Optional<Customer> payeeOpt = customerService.getCustomerById(payment.getPayee().getId()); // fixed

        if (payerOpt.isPresent() && payeeOpt.isPresent()) {
            Customer payer = payerOpt.get();
            Customer payee = payeeOpt.get();

            // Call transfer method from TransactionService
            transactionService.transferMoney(payer, payee, payment.getAmount());

            // Update last payment date
            payment.setLastPaymentDate(java.time.LocalDateTime.now());
            recurringPaymentRepository.save(payment);
        }
    }

    @Override
    public void deactivateRecurringPayment(Long paymentId) {
        recurringPaymentRepository.findById(paymentId).ifPresent(payment -> {
            payment.setActive(false);
            recurringPaymentRepository.save(payment);
        });
    }

    @Override
    public Optional<RecurringPayment> getRecurringPaymentById(Long paymentId) {
        return recurringPaymentRepository.findById(paymentId);
    }
}
