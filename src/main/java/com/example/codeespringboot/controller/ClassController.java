package com.example.codeespringboot.controller;


import com.example.codeespringboot.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClassController {
    @Autowired
    private ClassService classService;
}
