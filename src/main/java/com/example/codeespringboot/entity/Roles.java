package com.example.codeespringboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "role")
@Table(name = "role")
@Data
@NoArgsConstructor
public class Roles implements Serializable {
    private static final long serialVersionUID = -5204391003825277886L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users = new ArrayList<>();

    public Roles(String name) {
        this.name = name;
    }
}