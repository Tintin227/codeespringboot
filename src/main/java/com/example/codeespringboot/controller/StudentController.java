package com.example.codeespringboot.controller;

import com.example.codeespringboot.dto.SearchStudentDto;
import com.example.codeespringboot.dto.StudentClassDto;
import com.example.codeespringboot.dto.StudentDto;
import com.example.codeespringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
@Transactional
public class StudentController {
    @Autowired
    private StudentService studentService;
    //API add student
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<StudentDto> addStudent(@RequestBody StudentClassDto studentClassDto){
        try {
            return studentService.addStudent(studentClassDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<StudentDto>();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Page<StudentDto> getListStudentByClass(@RequestParam(required = false) String classCode,@RequestParam(required = false) String studentName, @RequestParam int pageNo, @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        SearchStudentDto search = new SearchStudentDto();
        search.setClassCode(classCode);
        search.setStudentName(studentName);
        return studentService.findAll(search,pageable);
    }

//    @GetMapping(value = "/findStudentsByName")
//    public Page<StudentDto> findStudentsByName(@RequestParam(required = false) String studentName, @RequestParam int pageNo, @RequestParam int pageSize){
//        Pageable pageable = PageRequest.of(pageNo,pageSize);
//        return studentService.findStudentsByName(studentName,pageable);
//    }

    //API update student
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentDto updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto){
        return studentService.updateStudent(id, studentDto);
    }

    //Delete theo id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public boolean deleteStudent(@PathVariable("id") Long id){
         studentService.deleteStudentsById(id);
        return true;
    }


}
