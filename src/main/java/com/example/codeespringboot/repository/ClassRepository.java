package com.example.codeespringboot.repository;


import com.example.codeespringboot.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Classes,Long> {
    Optional<Classes> findByCode(String code);
}
