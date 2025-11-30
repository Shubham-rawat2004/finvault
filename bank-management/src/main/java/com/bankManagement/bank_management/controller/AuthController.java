//package com.bankManagement.bank_management.controller;
//
//import com.bankManagement.bank_management.model.AuthRequest;
////import com.bankManagement.bank_management.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
////    private JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest request) {
//        Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        String role = userDetails.getAuthorities().iterator().next().getAuthority();
//
////        return jwtUtil.generateToken(userDetails.getUsername(), role);
//    }
//}
