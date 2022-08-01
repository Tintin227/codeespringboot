package com.example.codeespringboot.repository;


import com.example.codeespringboot.entity.StudentClasses;
import com.example.codeespringboot.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentClassRepository extends JpaRepository<StudentClasses, Long> {
    @Query("select sc.students from StudentClasses sc where sc.classes.code = :classCode")
    Page<Students> findListStudentByClassCode(String classCode, Pageable pageable);

    /*@Query("delete from StudentClasses sc where sc.studentId = :id ")*/
    void deleteByStudentId(Long id);

}
