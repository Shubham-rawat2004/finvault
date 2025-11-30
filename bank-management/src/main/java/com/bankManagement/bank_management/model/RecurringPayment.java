package com.bankManagement.bank_management.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_payments")
public class RecurringPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Customer payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private Customer payee;

    private double amount;

    private String frequency; // DAILY, WEEKLY, MONTHLY

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean active;

    private LocalDateTime lastPaymentDate;

    public RecurringPayment() {
    }

    public RecurringPayment(Customer payer, Customer payee, double amount, String frequency, LocalDate startDate, LocalDate endDate) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
        this.lastPaymentDate = null; // payment not processed yet
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Customer getPayer() {
        return payer;
    }

    public void setPayer(Customer payer) {
        this.payer = payer;
    }

    public Customer getPayee() {
        return payee;
    }

    public void setPayee(Customer payee) {
        this.payee = payee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }
}
