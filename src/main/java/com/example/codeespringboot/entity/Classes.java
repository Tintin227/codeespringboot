package com.example.codeespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;


}
