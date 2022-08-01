package com.example.codeespringboot.service;

import com.example.codeespringboot.dto.ClassDto;
import com.example.codeespringboot.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
   @Autowired
    private ClassRepository classRepository;


    @Override
    public List<ClassDto> addClass(ClassDto classDto) {
        return null;
    }
}
