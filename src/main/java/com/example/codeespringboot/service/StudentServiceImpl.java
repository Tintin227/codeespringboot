package com.example.codeespringboot.service;


import com.example.codeespringboot.dto.SearchStudentDto;
import com.example.codeespringboot.dto.StudentClassDto;
import com.example.codeespringboot.dto.StudentDto;
import com.example.codeespringboot.entity.Classes;
import com.example.codeespringboot.entity.StudentClasses;
import com.example.codeespringboot.entity.Students;
import com.example.codeespringboot.repository.ClassRepository;
import com.example.codeespringboot.repository.StudentClassRepository;
import com.example.codeespringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentClassRepository studentClassRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<StudentDto> addStudent(StudentClassDto studentClassDto) {
        boolean existedStudent = studentRepository.existsByCode(studentClassDto.getStudentCode());
        if (existedStudent){
            throw new RuntimeException("Ma sinh vien da ton tai!");
        }
        Optional<Classes> classesOptional = classRepository.findByCode(studentClassDto.getClassCode());
        long ClassId = 0;
        if (classesOptional.isEmpty()){
            Classes classes = new Classes();
            classes.setName(studentClassDto.getClassName());
            classes.setCode(studentClassDto.getClassCode());
            classRepository.save(classes);
            ClassId = classes.getId();
        } else {
            ClassId = classesOptional.get().getId();
        }
        Students students = new Students();
        students.setName(studentClassDto.getStudentName());
        students.setCode(studentClassDto.getStudentCode());
        studentRepository.save(students);
        long StudentId = students.getId();
        StudentClasses studentClasses = new StudentClasses();
        studentClasses.setStudentId(StudentId);
        studentClasses.setClassId(ClassId);
        studentClassRepository.save(studentClasses);
        return null;
    }

    @Override
    public Page<StudentDto> getListStudentByClass(String classCode, Pageable pageable) {
        return studentClassRepository.findListStudentByClassCode(classCode, pageable).map(StudentDto::new);
    }

    @Override
    public Page<StudentDto> findStudentsByName(String studentName, Pageable pageable) {
        return studentRepository.findStudentsByName(studentName,pageable).map(StudentDto::new);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
            Optional<Students> studentsOptional = studentRepository.findById(id);
            if (!studentsOptional.isEmpty()){
                Students students = studentsOptional.get();
                students.setCode(studentDto.getCode());
                students.setName(studentDto.getName());
                studentRepository.save(students);
        }
        return null;
    }

    @Override
    public boolean deleteStudentsById(Long id) {
//        studentClassRepository.deleteStudentClassesByStudentId(id);
//         studentRepository.deleteStudentsById(id);
        studentClassRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<StudentDto> findAll(SearchStudentDto searchDto, Pageable pageable) {
        String sql = "select sc.students from StudentClasses sc where 1=1 ";
        String countTotal = "select count(sc.students) from StudentClasses sc where 1=1 ";
        Map<String, String> parameters = new HashMap<>();
        if (searchDto.getStudentName() != null) {
            sql += "and sc.students.name = :studentName";
            countTotal += "and sc.students.name = :studentName";
            parameters.put("studentName",searchDto.getStudentName());
        }
        if (searchDto.getStudentCode() != null){
            sql += "and sc.students.code = :studentCode";
            countTotal += "and sc.students.code = :studentCode";
            parameters.put("studentCode", searchDto.getStudentCode());
        }
        if (searchDto.getClassName() != null){
            sql += "and sc.classes.name = :studentCode";
            countTotal += "and sc.students.code = :className";
            parameters.put("className", searchDto.getClassName());
        }
        if (searchDto.getClassCode() != null) {
            sql += " and sc.classes.code = :classCode";
            countTotal += " and sc.classes.code = :classCode";
            parameters.put("classCode", searchDto.getClassCode());
        }

        Query query =  entityManager.createQuery(sql,Students.class);
        parameters.keySet().forEach(key -> query.setParameter(key,parameters.get(key)));
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Students> listStudents = query.getResultList();
        List<StudentDto> result = listStudents.stream().map(StudentDto::new).collect(Collectors.toList());

        Query queryTotal =  entityManager.createQuery(countTotal);
        parameters.keySet().forEach(key -> queryTotal.setParameter(key,parameters.get(key)));
        long total = (long) queryTotal.getSingleResult();

        return new PageImpl<>(result,pageable,total);
    }


    }


