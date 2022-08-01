package com.example.codeespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "student_class_rel")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "class_id")
    private Long classId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Students students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id",insertable = false, updatable = false)
    private Classes classes;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}