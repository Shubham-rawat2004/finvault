package com.bankManagement.bank_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Welcome to USER Dashboard!";
    }
}
