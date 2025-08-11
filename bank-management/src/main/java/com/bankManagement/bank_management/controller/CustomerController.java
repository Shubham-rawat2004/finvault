package com.bankManagement.bank_management.controller;
import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import  java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        return customerService.createCustomer((customer));
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping(value = "/{id}",name = "sdfsdf")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody @Valid Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
     @DeleteMapping("/{id}")
public String deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return "Customer deleted Successfully with id "+id;
    }
    @GetMapping("/hello")
    public String hello(){
        return "Customer Controller  is working!";
    }
}
