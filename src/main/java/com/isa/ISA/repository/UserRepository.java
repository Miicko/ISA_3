package com.isa.ISA.repository;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    UserDetails findByLogin(String email);
}
