package com.example.codeespringboot.repository;


import com.example.codeespringboot.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Students, Long>, JpaSpecificationExecutor<Students> {
    boolean existsByCode(String code);
    @Query("select s from Students s where s.name = :studentName")
    Page<Students> findStudentsByName(String studentName, Pageable pageable);

    /*@Query("delete from Students s where s.id = :id ")*/
    void deleteById(Long id);

}
