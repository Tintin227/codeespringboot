/*
package com.example.codeespringboot.entity;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "post")
@Table(name = "post")
@Data
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users user;  //Tác giả viết post

    @JsonGetter(value = "author")
    public String getUserName() {
        return user.getUsername();
    }

}
*/
