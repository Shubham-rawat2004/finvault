package com.bankManagement.bank_management.model;
import com.bankManagement.bank_management.controller.CustomerController;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name is required")
    private String name;
      @Email(message = "Email should be valid")
      @NotBlank(message = "Email is required")

    private String email;
      @Min(value=0,message = "Balance must be zero or more")
    private double balance;

    public Customer(){

    }
    public Customer(long anonymous,String name,String email
    ,double balance){
        this.id = anonymous;
        this.name=name;
        this.email=email;
        this.balance=balance;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id ){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void  setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
}

