package com.example.codeespringboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity(name = "users")
@Table(name = "users")
@Data
@NoArgsConstructor //Phải có không là báo lỗi No default constructor for entity vn.techmaster.authorization.model.User
public class Users implements UserDetails {
    private static final long serialVersionUID = 6268404888144025944L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    public void addRole(Roles role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Roles role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

  /*  //--- Constructor --------------------------------
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }*/


    //-------- Implements các methods của interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    //---@GET
    //Một User viết nhiều Post
   /* @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<Posts> posts = new ArrayList<>();
    public void addPost(Posts post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removePost(Posts post) {
        posts.remove(post);
        post.setUser(null);
    }*/
}
