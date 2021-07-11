package com.javatechie.spring.ajax.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.UsersRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByphoneNumber(username);
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getOtp(), new ArrayList<>());
    }
}
