package com.example.codeespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchStudentDto {
    private String studentName;
    private String classCode;
    private String studentCode;
    private String className;

}
