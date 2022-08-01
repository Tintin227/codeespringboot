/*
package com.example.codeespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "users_roles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",insertable = false, updatable = false)
    private Roles roles;
}
*/
