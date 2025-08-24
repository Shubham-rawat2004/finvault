package com.bankManagement.bank_management.repository;

import com.bankManagement.bank_management.model.RecurringPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringPaymentRepository extends JpaRepository<RecurringPayment, Long> {

    // Fetch all active recurring payments
    List<RecurringPayment> findByActiveTrue();

    // Optional: fetch by payer
    List<RecurringPayment> findByPayerIdAndActiveTrue(Long payerId);
}
