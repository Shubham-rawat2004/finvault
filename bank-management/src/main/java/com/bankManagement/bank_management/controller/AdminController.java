package com.bankManagement.bank_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to ADMIN Dashboard!";
    }
}
