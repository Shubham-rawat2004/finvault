package com.bankManagement.bank_management.repository;

import com.bankManagement.bank_management.model.User;  // ✅ corrected import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
