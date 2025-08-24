package com.bankManagement.bank_management.controller;
import com.bankManagement.bank_management.model.Customer;
import com.bankManagement.bank_management.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        customer.setId(null); // force Hibernate to treat as NEW row
        System.out.println("Creating customer: " + customer);
        return customerService.createCustomer(customer);
    }

    @GetMapping("/all")
    public Page<Customer> getAllCustomersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return customerService.getAllCustomersPaginated(pageable);
    }
    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @RequestBody @Valid Customer customer) {
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
    @GetMapping("/search")
    public List<Customer> searchByName(@RequestParam String name){
        return customerService.searchCustomerByName(name);
    }
    @GetMapping("/email")
    public Optional<Customer> getByEmail(@RequestParam String email){
        return customerService.getCustomerByEmail(email);
    }
    @PostMapping("/{id}/deposit")
    public Customer deposit(@PathVariable Long id, @RequestParam double amount) {
        return customerService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Customer withdraw(@PathVariable Long id, @RequestParam double amount) {
        return customerService.withdraw(id, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromId,
                           @RequestParam Long toId,
                           @RequestParam double amount) {
        customerService.transfer(fromId, toId, amount);
        return "Transfer successful from customer " + fromId + " to " + toId;
    }
}
