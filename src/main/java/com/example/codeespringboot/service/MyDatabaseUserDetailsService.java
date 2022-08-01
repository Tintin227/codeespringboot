package com.example.codeespringboot.service;

import com.example.codeespringboot.entity.Users;
import com.example.codeespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        List<SimpleGrantedAuthority> grantedAuthorities = users.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        return new User(users.getUsername(), users.getPassword(), grantedAuthorities);
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }*/
}
