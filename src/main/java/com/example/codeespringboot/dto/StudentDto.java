package com.example.codeespringboot.dto;

import com.example.codeespringboot.entity.Students;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String code;


    public StudentDto(Students student) {
        BeanUtils.copyProperties(student,this);
    }
}
