package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void authenticate(String username, String password);

    void saveUser(String username, String password);
}
