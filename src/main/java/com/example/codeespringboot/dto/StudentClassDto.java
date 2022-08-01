package com.example.codeespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassDto {
    private String studentCode;
    private String studentName;
    private String classCode;
    private String className;
    private String username;
    private String password;
}
