package com.example.codeespringboot.service;


import com.example.codeespringboot.dto.SearchStudentDto;
import com.example.codeespringboot.dto.StudentClassDto;
import com.example.codeespringboot.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public List<StudentDto> addStudent(StudentClassDto studentClassDto);

    Page<StudentDto> getListStudentByClass(String classCode, Pageable pageable);

    Page<StudentDto> findAll(SearchStudentDto searchDto, Pageable pageable);

    Page<StudentDto> findStudentsByName(String studentName, Pageable pageable);

    public StudentDto updateStudent(Long id, StudentDto studentDto);

    public boolean deleteStudentsById(Long id);





}
