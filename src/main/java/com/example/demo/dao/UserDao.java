package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao {
    User findUserByName(String username);
    void saveUser(String username, String password);
}
