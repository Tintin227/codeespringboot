package com.example.codeespringboot.repository;

import com.example.codeespringboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    public Users findByUsername(String username);
}
