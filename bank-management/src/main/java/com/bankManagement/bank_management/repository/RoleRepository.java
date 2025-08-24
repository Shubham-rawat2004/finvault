package com.bankManagement.bank_management.repository;

import com.bankManagement.bank_management.model.Role;  // ✅ corrected import
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
