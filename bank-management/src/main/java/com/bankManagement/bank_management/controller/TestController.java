package com.bankManagement.bank_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String helloPublic() {
        return "Hello, this is a PUBLIC endpoint!";
    }

    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello USER, you are authenticated!";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello ADMIN, you are authenticated!";
    }
}
