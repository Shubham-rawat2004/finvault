package com.bankManagement.bank_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Constructors
    public Transaction() {}

    public Transaction(double amount, String type, Customer customer) {
        this.amount = amount;
        this.type = type;
        this.customer = customer;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }


}
